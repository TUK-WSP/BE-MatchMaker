package org.wsp.matchmaker.domain.group.repository;

import org.wsp.matchmaker.domain.group.entity.Group;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GroupRepository {

    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    public void save(Group group) {
        String sql = "INSERT INTO `group` (group_id, group_name, group_description, status, group_thumbnail_image_url) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, group.getGroupId().toString());
            pstmt.setString(2, group.getGroupName());
            pstmt.setString(3, group.getGroupDescription());
            pstmt.setString(4, group.getStatus().name());
            pstmt.setString(5, group.getGroupThumbnailImageUrl());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
