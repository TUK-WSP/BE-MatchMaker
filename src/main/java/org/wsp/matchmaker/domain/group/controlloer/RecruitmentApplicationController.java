package org.wsp.matchmaker.domain.group.controlloer;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/recruitment/application")
public class RecruitmentApplicationController extends HttpServlet {

    // JSON 파싱 메서드
    private static String getParamFromJson(String json, String key) {
        // 파싱: "application_id":"..." 형태를 가정
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
        // 요청 바디를 읽어 JSON 파싱
        StringBuilder sb = new StringBuilder();
        try(BufferedReader br = request.getReader()) {
            String line;
            while((line = br.readLine()) != null) {
                sb.append(line);
            }
        }
        String requestBody = sb.toString();
        String applicationId = getParamFromJson(requestBody, "application_id");

        // 여기서는 mock 데이터 반환
        String recruitmentId = "r-001";
        String application_id = (applicationId != null) ? applicationId : "unknown";
        String application_status = "APPROVED";
        String user_id = "user123";
        String application = "간단한 자기소개";

        // JSON 응답 생성
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print("{");
        out.print("\"recruitmentId\":\""+recruitmentId+"\",");
        out.print("\"recruitmentApplication\":{");
        out.print("\"application_id\":\""+application_id+"\",");
        out.print("\"application_status\":\""+application_status+"\",");
        out.print("\"user_id\":\""+user_id+"\",");
        out.print("\"application\":\""+application+"\"");
        out.print("}}");
        out.flush();
    }
}
