package org.wsp.matchmaker.domain.group.controlloer;

;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/recruitment/answer")
public class RecruitmentAnswerController extends HttpServlet {

    // JSON 파싱
    private String getParamFromJson(String json, String key) {
        String search = "\"" + key + "\":\"";
        int start = json.indexOf(search);
        if (start == -1) return null;
        start += search.length();
        int end = json.indexOf("\"", start);
        if (end == -1) return null;
        return json.substring(start, end);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청 바디 JSON 읽기
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = request.getReader()) {
            String line;
            while((line = br.readLine()) != null) {
                sb.append(line);
            }
        }
        String requestBody = sb.toString();
        String answerId = getParamFromJson(requestBody, "answer_id");

        // 여기서는 mock 데이터 반환
        // {
        //   "userGroups": [
        //     {
        //       "userGroupId": "...",
        //       "group": {
        //         "groupId": "...",
        //         "groupName": "...",
        //         "groupThumbnailImageUrl": "...",
        //         "groupDescription": "..."
        //       }
        //     }
        //   ]
        // }

        // mock 데이터
        String userGroupId = "1001";
        String groupId = "g-123";
        String groupName = "Sample Group";
        String groupThumbnailImageUrl = "https://example.com/img.png";
        String groupDescription = "This is a sample group description.";

        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.print("{");
        out.print("\"userGroups\":[");
        out.print("{");
        out.print("\"userGroupId\":\""+userGroupId+"\",");
        out.print("\"group\":{");
        out.print("\"groupId\":\""+groupId+"\",");
        out.print("\"groupName\":\""+groupName+"\",");
        out.print("\"groupThumbnailImageUrl\":\""+groupThumbnailImageUrl+"\",");
        out.print("\"groupDescription\":\""+groupDescription+"\"");
        out.print("}");
        out.print("}");
        out.print("]}");

        out.flush();
    }
}
