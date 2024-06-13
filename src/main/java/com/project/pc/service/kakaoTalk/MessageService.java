package com.project.pc.service.kakaoTalk;

import com.project.pc.dto.KakaoMessageDTO;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
public class MessageService extends HttpCallService{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String MSG_SEND_SERVICE_URL = "https://kapi.kakao.com/v2/api/talk/memo/default/send";
    private static final String SEND_SUCCESS_MSG = "메시지 전송에 성공했습니다.";
    private static final String SEND_FAIL_MSG = "메시지 전송에 실패했습니다.";

    private static final String SUCCESS_CODE = "0";

    public static String authToken;

    public boolean sendMessage(){
        JSONObject linkObj  = new JSONObject();

        linkObj.put("web_url", "");
        linkObj.put("mobile_web_url", "");

        JSONObject templateObj = new JSONObject();
        templateObj.put("object_type", "text");
        templateObj.put("text", "문의 채팅 요청이 들어왔습니다!");
        templateObj.put("link", linkObj);
        templateObj.put("button_title", "문의 요청");

        HttpHeaders header = new HttpHeaders();
        header.set("Content-Type", APP_TYPE_URL_ENCODED);
        header.set("Authorization", "Bearer " + authToken);

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("template_object", templateObj.toString());

        HttpEntity<?> messageRequestEntity = httpClientEntity(header, parameters);

        String resultCode = "";
        ResponseEntity<String> response = httpRequest(MSG_SEND_SERVICE_URL, HttpMethod.POST, messageRequestEntity);
        JSONObject jsonData = new JSONObject(response.getBody());
        resultCode = jsonData.get("result_code").toString();

        return successCheck(resultCode);
    }

    public boolean successCheck(String resultCode) {
        if(resultCode.equals(SUCCESS_CODE)) {
            logger.info(SEND_SUCCESS_MSG);
            return true;
        }else {
            logger.debug(SEND_FAIL_MSG);
            return false;
        }
    }
}
