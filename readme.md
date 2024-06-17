![join](https://github.com/appcoding-ux/pcProject/assets/112378228/84ebe1ac-ace9-423d-8a62-44cb1c28f33f)![header](https://capsule-render.vercel.app/api?type=venom&height=300&color=gradient&text=PC%20이용%20및%20관리%20프로젝트%20Spting%20Boot&fontSize=36&textBg=false&fontColor=fff)
<div align=center>
  <h3>✌프로젝트 소개😊</h3>
  스프링 부트 JPA를 이용하여<br>
  PC방 이용 및 관리 프로젝트를 구현해보았습니다.<br>
  PC이용, 요금제 구매, 먹거리 구매<br>
  웹소켓(WebSocket)을 이용한 실시간 문의 채팅기능<br>
  카카오 로그인, 좌석관리 , 회원관리, 음식관리, 판매통계 등 여러가지 기능을 구현해보았습니다.<br>
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
  
  ![erd](https://github.com/appcoding-ux/pcProject/assets/112378228/ca1977b1-0fa9-463c-8ce8-52e7916ad5df)
</details>



  🧸CLASS 다이어그램


  * **컨트롤러(Controller)**
    
    ![controller](https://github.com/appcoding-ux/pcProject/assets/112378228/f6e69678-9f46-4be5-9016-d6725f8858b0)

  * **테이블(Entity)**
    
     ![domain](https://github.com/appcoding-ux/pcProject/assets/112378228/c8df79ac-9ad0-4905-972e-11078d348714)

  * **데이터 전송 객체(DTO)**
    
     ![dto](https://github.com/appcoding-ux/pcProject/assets/112378228/bbec7d76-8ede-4d16-8ae6-61012839d3b5)

  * **디지털 스토리지(Repository)**
    
    ![repository](https://github.com/appcoding-ux/pcProject/assets/112378228/f68e2a13-da82-434b-84b8-965278d1bb82)

  * **서비스(Service)**

    ![service](https://github.com/appcoding-ux/pcProject/assets/112378228/f792b6fd-9a84-4b6b-87f8-ff864361477a)

  * **웹소켓(WebSocket)**
    
    ![websocket](https://github.com/appcoding-ux/pcProject/assets/112378228/4e333818-1021-4887-b82f-9950e519b1af)

  * **설정 클래스(Configuration)**

    ![config](https://github.com/appcoding-ux/pcProject/assets/112378228/b683cc23-3d7b-453f-a32a-fd9e4ad8166c)


  * **🔒스프링 시큐티리 부분**

    ![security](https://github.com/appcoding-ux/pcProject/assets/112378228/b7a98195-5aab-48f3-a1b3-82d8fcb92afa)

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
- 회원의 실시간 채팅 요청 시 관리자에게 알림기능<br>
  (해당 기능은 관리자가 카카오 로그인을 했을 경우에만 실행되기 때문에 실질적으로 동작하지 않음)

#### 주문
- 다양한 음식 먹거리를 주문
- 카테고리 별 메뉴 검색
- 검색을 통한 원하는 메뉴 검색

#### PC 이용
- 로그인을 통해 빈자리 선택 후 PC 이용
- 자리를 선택하기 전 시간 충전 가능
- 남아있는 시간이 없다면 시간 충전 후 PC 이용

  
# 📹시연 영상

* **회원가입 및 로그인(메인 화면)**
  * `아이디 중복 확인`과 `이메일 인증`을 진행하고 여러 `예외처리`를 거쳐<br>
  회원가입을 진행할 수 있습니다.

    ![join](https://github.com/appcoding-ux/pcProject/assets/112378228/3d684f2c-72cc-46c1-a7cf-b4b11721efcd)

* **비밀번호 변경**
  * `로그인 화면`에서 `비밀번호 찾기`를 통해 `이메일 인증`을 하고 `비밀번호 변경`을 할 수 있습니다
    
    ![join](https://github.com/appcoding-ux/pcProject/assets/112378228/edd5b80e-84a2-463d-a650-7fbbd4a52434)

* **PC 이용 자리 선택 & 시간 충전**
  * `정삭적으로 로그인`이 되었을 때 `비어있는 자리`를 `이용`할 수 있습니다.
    ![loginAfterSeatSelect](https://github.com/appcoding-ux/pcProject/assets/112378228/9bdef869-bf73-4557-a78a-459cce68a223)
 
  * 만약 `시간`이 없을 경우에는 `시간 충전` 후 PC이용이 가능합니다.
    ![loginAfterBuyTime](https://github.com/appcoding-ux/pcProject/assets/112378228/c73cf5e4-3fae-42ff-bc62-66ce58c590e7)
 
  * `PC이용 중`에도 원하는 시간만큼 `결제`를 통해 `충전`이 가능합니다.
    ![usePCBuyTime](https://github.com/appcoding-ux/pcProject/assets/112378228/0d278cbd-4aa0-4d79-aeb1-37dc01f8342d)
 

* **음식검색**
  * `카테고리별` 음식을 검색할 수 있습니다.
    ![foodCategory](https://github.com/appcoding-ux/pcProject/assets/112378228/517ba946-c54d-44e4-9e21-3d9992170005)
 
  * `검색`을 통해 음식을 검색할 수 있습니다.
    ![foodCategory](https://github.com/appcoding-ux/pcProject/assets/112378228/3fa05e06-1624-4f51-ab41-08638b0878bc)
    
* **음식주문**
  * `제품 선택` 후 `수량` 및 `선택 상품 삭제`, `요청메세지` 작성을 마친 후<br>
  음식을 `주문`할 수 있습니다. (만약, 선택한 음식이 없으면 주문할 수 없습니다!)
    ![foodOrder](https://github.com/appcoding-ux/pcProject/assets/112378228/ad99169a-a962-4dae-b4ab-5b502d51aec2)

* **Q&A**
  * `PC 이용` 중 `관리자 문의채팅`을 할 수 있습니다. <br>
  (관리자가 채팅에 들어와야 채팅이 가능합니다! 관리자 입장 전 문자입력이 제한됩니다.)
    ![userQ A](https://github.com/appcoding-ux/pcProject/assets/112378228/a2fe8785-dd7e-4ff0-a477-1000c42437de)
 
  * `문의해결` 시 `X(닫기)버튼`을 통해 `채팅종료`를 할 수 있습니다.
    ![userQ AClose](https://github.com/appcoding-ux/pcProject/assets/112378228/c3bd01e4-2c8d-4f8e-8722-cb74db0062ef)

 
  * 만약 `채팅 입력` 후 2분 경과 시 `자동 종료`됩니다. <br>
  (해당 부분은 2분 넘게 영상녹화를 할 수 없어서 영상이 없는 점 양해 부탁드립니다.)


* **관리자 페이지**
  * `자리` 탭에서 현재 `사용되고 있는` 좌석에 `사용자 ID`가 표시됩니다. <br>
  (이 부분에서 관리자가 강제로 로그아웃 기능을 구현하고 싶었으나 Security로 인해 불가능했습니다..)
    ![adminSeat](https://github.com/appcoding-ux/pcProject/assets/112378228/41ecfa04-bc15-4c02-822f-5b3ac129bc00)

  * `음식` 탭에서 `음식 CRUD` 및 `검색`이 가능합니다
    * **음식 추가**
      ![adminFoodInsert](https://github.com/appcoding-ux/pcProject/assets/112378228/791f18e8-97d4-4c82-985d-cae51b3f6675)
   
    * **음식 수정 & 음식 정보 확인**
      ![adminFoodRead Update](https://github.com/appcoding-ux/pcProject/assets/112378228/f81dd4d8-32dc-4e6a-bd69-7f6a6a4758d4)
   
    * **음식 삭제**
      ![adminFoodRead Update](https://github.com/appcoding-ux/pcProject/assets/112378228/e7d9a52c-865c-4f04-8b8c-45e706d484b6)
   
    * **음식 검색 & 페이징**
      ![adminFoodSearch-min](https://github.com/appcoding-ux/pcProject/assets/112378228/7fb1cef4-15de-4f9d-842d-2a6d45aa1863)
  

  * `판매내역` 탭에서 `최근 일주일 매출` 및 `음식 카테고리 별 판매 갯수`를 확인할 수 있습니다.
    ![adminSale](https://github.com/appcoding-ux/pcProject/assets/112378228/0235f118-8981-410d-b232-531cce100744)
 
  * `회원관리` 탭에서 현재 가입되어 있는 회원을 볼 수 있고 `회원 비활성화`를 할 수 있습니다.
    ![adminUser](https://github.com/appcoding-ux/pcProject/assets/112378228/cc7b66c1-c37a-45c7-8671-cb8a0d387a17)
 
  * `문의관리` 탭에서 현재 `문의 채팅`을 요청한 회원의 <br>
  `아이디`가 표시되고 `클릭` 시 `채팅방`으로 이동하여 `채팅`합니다
    ![adminQ A](https://github.com/appcoding-ux/pcProject/assets/112378228/5fe1a9b3-7ea7-4072-a69e-295e7fdddccc)

  * 회원이 채팅을 `종료`하면 `알림창`을 띄우고 `문의 목록 페이지`로 이동합니다.
    ![adminQ AOut](https://github.com/appcoding-ux/pcProject/assets/112378228/e9aed93d-4e3a-47e1-bcda-443e57bada4b)
  
## 💪프로젝트 소감
처음 인텔리제이와 스프링 부트를 이용해 프로젝트를 구성하고<br>
구현해보았는데 생각보다 혼자할 때 장점도 있지만 다른 사람과 의견을<br>
주고 받을 수 없다는 점이 크게 다가왔습니다.<br>
구현하고 싶은 기능을 다 구현한 것 같아 뿌듯하고 보람이 있었습니다.<br>
부족한 부분은 보완하고 잘한 부분은 반복해서 살펴보는 기회가 되었던 것 같습니다.

## 🚀개선사항
- 관리자가 PC를 이용중인 회원을 강제로 종료할 수 있는 기능
