package com.green.java.dao;

import com.green.java.dao.model.*;
import com.green.java.dao.model.BoardEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDao {
    private MyConnection mc;

    public BoardDao() {
        mc = new MyConnection();
    }

    public int insBoard(BoardEntity entity){

        int result = 0;
        String sql = "INSERT INTO t_board\n" +
                "SET title = ?" +
                ", ctnt = ? " +
                ", iuser = ? ";
        System.out.println(sql);

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = mc.getConn();
            ps = con.prepareStatement(sql);
            ps.setString(1,entity.getTitle());
            ps.setString(2,entity.getCtnt());
            ps.setInt(3,entity.getIuser());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public int updBoard(BoardEntity entity) {

        int result = 0;

        String sql = "update t_board\n" +
                "SET title = ?" +
                ", ctnt = ? " +
                ", updated_at = now() " +
                "WHERE " + "iboard = ? " + "AND " + "iuser = ?";
        System.out.println(sql);

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = mc.getConn();
            ps = con.prepareStatement(sql);

            ps.setString(1,entity.getTitle());
            ps.setString(2,entity.getCtnt());
            ps.setInt(3,entity.getIboard());
            ps.setInt(4,entity.getIuser());

            result = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public BoardDetailVo selBoardDetail(BoardSelDto dto) {
        String sql = "SELECT A.iboard, A.title, A.created_at createdAt, A.ctnt,\n" +
                "        B.nm writer, B.main_pic writerMainPic\n" +
                "        FROM t_board A\n" +
                "        INNER JOIN t_user B\n" +
                "        ON A.iuser = B.iuser\n" +
                "        where A.iboard = ?";

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        BoardDetailVo vo = null;

        try {
            con = mc.getConn();
            ps = con.prepareStatement(sql);
            ps.setInt(1, dto.getIboard());

            rs = ps.executeQuery();


            if (rs.next()) {

                int iboard = rs.getInt("iboard");
                String title = rs.getString("title");
                String ctnt = rs.getString("ctnt");
                String createdAt = rs.getString("createdAt");
                String writer = rs.getString("writer");
                String writerMainPic = rs.getString("writerMainPic");

                vo = new BoardDetailVo(iboard, title, ctnt, createdAt, writer, writerMainPic);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return vo;
    }

    public List<BoardVo> selBoard(BoardSelDto dto){
        List<BoardVo> list = new ArrayList<>();
        String sql = "SELECT A.iboard, A.title, A.created_at createdAt, A.ctnt,\n" +
                "        B.nm writer, B.main_pic writerMainPic\n" +
                "        FROM t_board A\n" +
                "        INNER JOIN t_user B\n" +
                "        ON A.iuser = B.iuser" +
                "        ORDER BY iboard DESC\n" +
                "        LIMIT ?, ? ";

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = mc.getConn();
            ps = con.prepareStatement(sql);
            ps.setInt(1, dto.getStartIdx());
            ps.setInt(2, dto.getRow());

            rs = ps.executeQuery();


            while (rs.next()) { // 다음 레코드가 없으면 false가 뜬다
                int iboard = rs.getInt("iboard");
                String title = rs.getString("title");
                String createdAt = rs.getString("createdAt");
                String writer = rs.getString("writer");
                String writerMainPic = rs.getString("writerMainPic");

                BoardVo vo = new BoardVo(iboard, title, createdAt, writer, writerMainPic);
                list.add(vo);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return list;
    }
}
