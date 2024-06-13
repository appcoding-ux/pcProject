![header](https://capsule-render.vercel.app/api?type=venom&height=300&color=gradient&text=PC방%20이용%20및%20관리%20프로젝트%20Spting%20Boot&fontSize=36&textBg=false&fontColor=fff)
<div align=center>
  <h3>✌프로젝트 소개😊</h3>
  스프링 부트 JPA를 이용하여<br>
  PC방 이용 및 관리 프로젝트를 구현해보았습니다.<br>
  PC이용, 요금제 구매, 먹거리 구매<br>
  웹소켓(WebSocket)을 이용한 실시간 문의 채팅기능<br>
  카카오 로그인, 자리관리 , 회원관리, 음식관리, 판매통계 등 여러가지 기능을 구현해보았습니다.<br>
</div>
<hr><br>


## 🚩 목차

1. [🧑‍💻 개발환경](#개발-환경)<br>
2. [📊 Diagram](#다이어그램)<br>
3. [💻 핵심기능](#핵심기능)<br>
4. [📹 시연 영상](#시연-영상)<br>
5. [💪 프로젝트 소감](#프로젝트-소감)<br>
6. [🚀 개선사항](#개선사항)
<hr>

# 🧑‍💻개발 환경
<div align=center>
  <h3>:ant:Used Skill</h3>
    <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">
    <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"/>
    <img src="https://img.shields.io/badge/spring Security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white"/>
    <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=JavaScript&logoColor=white"/>
    <img src="https://img.shields.io/badge/css3-1572B6?style=for-the-badge&logo=css3&logoColor=white"/>
    <img src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=HTML5&logoColor=white"/>
    <img src="https://img.shields.io/badge/jquery-0769AD?style=for-the-badge&logo=jquery&logoColor=white">
    <br>
    <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
    <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"/>
    <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white"/>
    <img src="https://img.shields.io/badge/Chart.js-FF6384?style=for-the-badge&logo=Chart.js&logoColor=white"/>
    <img src="https://img.shields.io/badge/apachetomcat-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=white"/>
    <img src="https://img.shields.io/badge/bootstrap-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white"/>
  <h3>:palm_tree:Used API</h3>
      <img src="https://img.shields.io/badge/KAKAO-FFCD00?style=for-the-badge&logo=kakao&logoColor=white"/>
      <img src="https://img.shields.io/badge/PortOne-FF6633?style=for-the-badge&logo=passport&logoColor=white"/>

  <h3>🤞 UI/UX</h3>
      <img src="https://img.shields.io/badge/Tistory(WSSS)-000000?style=for-the-badge&logo=tistory&logoColor=white"/>
</div>

# 📊다이어그램

  🧸usecase 다이어그램

  아직 못만들었다..



  🧸ER 다이어그램
  
  ![image](https://github.com/appcoding-ux/pcProject/assets/112378228/513d0e66-2f63-47a8-b0fd-d04af8c411aa)
</details>



  🧸CLASS 다이어그램


  * **컨트롤러(Controller)**
    
    ![controller](https://github.com/appcoding-ux/pcProject/assets/112378228/f06a348a-1ebd-41f9-bf81-2dab2f83d38c)

  * **테이블(Entity)**
    
     ![domain](https://github.com/appcoding-ux/pcProject/assets/112378228/01d7a8ac-5190-4a37-90ae-d0d9c40da434)

  * **데이터 전송 객체(DTO)**
    
     ![dto](https://github.com/appcoding-ux/pcProject/assets/112378228/6a9d550d-098e-464b-a1c6-c3a6c5ebba2a)

  * **디지털 스토리지(Repository)**
    
    ![repository](https://github.com/appcoding-ux/pcProject/assets/112378228/645fe588-f33e-40f9-8afd-9e205e164ca9)

  * **서비스(Service)**

    ![service](https://github.com/appcoding-ux/pcProject/assets/112378228/2fb338ac-50bc-402e-ab46-209932f13ec7)

  * **웹소켓(WebSocket)**
    
    ![websocket](https://github.com/appcoding-ux/pcProject/assets/112378228/bd92b2a0-f3d3-4fba-876f-afcb539b4b41)

  * **설정 클래스(Configuration)**

    ![config](https://github.com/appcoding-ux/pcProject/assets/112378228/74aeda22-4a1d-4741-b0c5-5d1ea9e9e89a)


  * **🔒스프링 시큐티리 부분**

    ![security](https://github.com/appcoding-ux/pcProject/assets/112378228/989ef40d-e06a-4d3a-b9c4-f43e22512053)

# 💻핵심기능

#### 회원
- 소셜 로그인으로 간편 회원가입
- 아이디 중복 처리
- 이메일 중복 처리
- 비밀번호 암호화를 통한 보안
- 이메일 인증을 통한 비밀번호 찾기
- 실시간 채팅을 통한 관리자에게 문의기능

#### 관리자
- 음식 CRUD
- 음식 판매 통계
- 회원 관리
- PC를 이용하는 회원 관리
- 문의 채팅에 대한 답변
- 회원의 실시간 채팅 요청 시 관리자에게 알림기능

#### 주문
- 다양한 음식 먹거리를 주문
- 카테고리 별 메뉴 검색
- 검색을 통한 원하는 메뉴 검색

#### PC 이용
- 로그인을 통해 빈자리 선택 후 PC 이용
- 자리를 선택하기 전 시간 충전
- 남아있는 시간이 없다면 시간 충전 후 PC 이용

  
# 📹시연 영상

* **회원가입 및 로그인(메인 화면)**
  * `아이디 중복 확인`과 `이메일 인증`을 진행하고 여러 `예외처리`를 거쳐<br>
  회원가입을 진행할 수 있습니다.

    
* **비밀번호 변경**
  * `로그인 화면`에서 `비밀번호 찾기`를 통해 `이메일 인증`을 하고 `비밀번호 변경`을 할 수 있습니다
    

* **PC 이용 자리 선택 & 시간 충전**
  * `정삭적으로 로그인`이 되었을 때 `비어있는 자리`를 `이용`할 수 있습니다.
 
  * 만약 `시간`이 없을 경우에는 `시간 충전` 후 PC이용이 가능합니다.
 
  * `PC이용 중`에도 원하는 시간만큼 `결제`를 통해 `충전`이 가능합니다.
 
* **음식검색**
  * 기본적인 검색으로 `메인페이지`에서 `상품이름`으로 검색이 가능합니다.

    
  * `색상`과 `상품이름`으로 이중으로 검색이 가능합니다. (색상은 선택)

    
* **음식주문**
  * `배송주소` 변경이 가능하고 `주소`를 저장하면 `배송지이름`과 같이 저장됩니다.

    
    
  * `요청메세지`를 입력할 수 있습니다.
    
    
    
  * 결제페이지로 가기 전에 `쿠폰`과 `적립금`을 사용할 수 있습니다

    
* **관리자 페이지**

  * `제품관리` 메뉴에서 상품을 등록하거나 재고 및 이미지등을, 수정, 삭제 할 수 있습니다.

  
  * `고객관리` 메뉴에서 비정상적인 고객을 비활성화 처리할 수 있습니다.
  
  
  * `공지사항` 메뉴에서 공지사항 등록, 수정, 삭제 할 수 있습니다.

  
  * `통계` 메뉴에서 나이대를 비교해 도충한 통계를 확인할 수 있습니다.
    
  
  * `문의답변` 메뉴에서 현재 답변하지 않은 문의들을 답변할 수 있습니다.

  
  
* **Q&A**
  * `Q&A` 메뉴에서 `회원`은 문의를 등록힐 수 있습니다.
  * 만약 더 궁금한 점이 있을 때 `재문의`를 할 수 있습니다. (단, 관리자가 답변을 했을 경우에만 가능합니다.)

## 💪프로젝트 소감
처음 인텔리제이와 스프링 부트를 이용해 프로젝트를 구성하고<br>
구현해보았는데 생각보다 혼자할 때 장점도 있지만 다른 사람과 의견을<br>
주고 받을 수 없다는 점이 크게 다가왔습니다.<br>
구현하고 싶은 기능을 다 구현한 것 같아 뿌듯하고 보람이 있었습니다.<br>
부족한 부분은 보완하고 잘한 부분은 반복해서 살펴보는 기회가 되었던 것 같습니다.

## 🚀개선사항
- 관리자가 PC를 이용중인 회원을 강제로 종료할 수 있는 기능
