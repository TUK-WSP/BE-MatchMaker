package org.wsp.matchmaker.domain.group.controlloer;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@WebServlet("/group/recruitment")
public class RecruitmentController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // GET 요청 시 JSP 페이지로 포워딩
        request.getRequestDispatcher("/WEB-INF/views/group/Recruitment.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // POST 요청: JSON 요청 처리
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = request.getReader()) {
            String line;
            while((line = br.readLine()) != null) {
                sb.append(line);
            }
        }
        String requestBody = sb.toString();
        Map<String,String> params = parseJson(requestBody);

        String groupId = params.get("groupId");
        String recruitmentTitle = params.get("recruitmentTitle");
        String recruitmentDescription = params.get("recruitmentDescription");
        String recruitmentDeadline = params.get("recruitmentDeadline");
        String recruitmentNumber = params.get("recruitmentNumber");
        String recruitmentStatus = params.get("recruitmentStatus");

        // recruitmentService.save(...)

        String recruitmentId = "r-" + UUID.randomUUID().toString();

        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print("{");
        out.print("\"recruitmentId\":\""+recruitmentId+"\",");
        out.print("\"groupId\":\""+(groupId!=null?groupId:"")+"\",");
        out.print("\"title\":\""+(recruitmentTitle!=null?recruitmentTitle:"")+"\",");
        out.print("\"description\":\""+(recruitmentDescription!=null?recruitmentDescription:"")+"\",");
        out.print("\"deadline\":\""+(recruitmentDeadline!=null?recruitmentDeadline:"")+"\",");
        out.print("\"recruitmentNumber\":\""+(recruitmentNumber!=null?recruitmentNumber:"")+"\"");
        out.print("}");
        out.flush();
    }

    private Map<String,String> parseJson(String json) {
        Map<String,String> map = new HashMap<>();
        String bodyTrim = json.trim().replaceAll("\\{","").replaceAll("\\}","");
        String[] pairs = bodyTrim.split(",");
        for (String pair : pairs) {
            String[] kv = pair.split(":");
            if (kv.length == 2) {
                String k = kv[0].trim().replaceAll("\"","");
                String v = kv[1].trim().replaceAll("\"","");
                map.put(k,v);
            }
        }
        return map;
    }
}
