var emailChcekCode;

var idOverlap = true;

// 이메일 유효성 검사 (이메일 정규표현식)
const emailRegex = /[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,3}/;

// 전화번호 유효성 검사 (전화번호 정규 표현식)
const phoneNumberRegex = /01[0-9]{1}[0-9]{3,4}[0-9]{4}/;

// 아이디 유효성 검사
const idRegex = /^[a-z][a-z0-9]*$/;

$(function(){
    $('.loginBtn').click(function (e) {
        e.preventDefault();

        let id = $('input[name="username"]').val();
        let password = $('input[name="password"]').val();
        let seat = $('input[name="seatNumber"]').val();

        if (id === "") {
            alert("아이디를 입력해주세요");
            return false;
        } else if (password === "") {
            alert("비밀번호를 입력해주세요");
            return false;
            // } else if (seat === "") {
            //     alert("좌석번호를 입력해주세요");
            //     return false;
            // } else if (parseInt(seat) < 0 || parseInt(seat) > 50){
            //     alert("좌석번호는 1부터 50까지 숫자를 입력해주세요");
            //     return false;
            // }
        }else {
            $('#loginForm').submit();
        }
    });

    $('.joinBtn').click(function (e){
        e.preventDefault();
        e.stopPropagation();

        let id = $('input[name="id"]').val();
        let password = $('.joinPass').val();
        let email = $('input[name="email"]').val();
        let phone = $('input[name="phone"]').val();
        let name = $('input[name="name"]').val();

        let emailCheck = emailDoubleCheck();

        if(id === ""){
            alert("아이디를 입력해주세요");
            return false;
        }else if(!idRegex.test(id)){
            alert("아이디에는 한글이 포함될 수 없습니다. \n또는 숫자로 시작할 수 없습니다.")
            return false;
        }else if(idOverlap){
            alert("아이디 중복체크를 해주세요");
            return false;
        }else if(password === ""){
            alert("비밀번호를 입력해주세요");
            return false;
        }else if(email === ""){
            alert("이메일을 입력해주세요");
            return false;
        }else if(!emailRegex.test(email)){
            alert("올바른 이메일을 입력해주세요");
            return false;
        }else if(!emailCheck){
            alert("이메일 인증을 진행해주세요");
            return false;
        }else if(name === ""){
            alert("이름을 입력해주세요");
            return false;
        }else if(phone === ""){
            alert("전화번호를 입력해주세요");
            return false;
        }else if(!phoneNumberRegex.test(phone)){
            alert("올바른 전화번호를 입력해주세요");
            return false;
        }else {
            $('#joinForm').submit();
        }
    });

    var token = $('input[name="_csrf"]').val();

    $('.emailCheckBtn').click(function(e){
        e.preventDefault();
        e.stopPropagation();

        let email = $('input[name="email"]').val();

        if(email === ""){
            alert("이메일을 입력해주세요");
            return false;
        }else if(!emailRegex.test(email)){
            alert("올바른 이메일 형식을 입력해주세요");
            return false;
        }else {
            $.ajax({
                type : 'POST',

                url : `/mail`,

                headers : {
                    "X-CSRF-TOKEN" : token
                },

                data : {
                    email : email
                },

                success : function(data) {
                    if(data === 'alreadyThere'){
                        alert("이미 가입된 이메일입니다.\n다른 이메일로 가입 부탁드립니다.");
                    }else {
                        alert("이메일 전송이 완료되었습니다.\n인증번호를 입력해주세요");
                        emailChcekCode = data;
                    }
                },

                error : function(jqXHR, textStatus, errorThrown) {
                    // 에러 처리
                    console.log("AJAX 요청 중 에러발생");
                    alert("에러 발생");
                }
            });
        }
    });

    $('.emailDoubleCheckBtn').click(function(e){
        e.preventDefault();
        e.stopPropagation();

        let checkCode = $('input[name="emailCheck"]').val();

        if(checkCode === emailChcekCode){
            alert("인증번호가 일치합니다.");
        }else {
            alert("인증번호가 불일치합니다.\n" +
                "다시 한 번 입력해주세요");
        }
    });

    $('.idCheckBtn').click(function(e){
        e.preventDefault();
        e.stopPropagation();

        let id = $('input[name="id"]').val();

        if(id === ""){
            alert("아이디를 입력해주세요");
        }else if(!idRegex.test(id)){
            alert("아이디에는 한글이 포함될 수 없습니다. \n또는 숫자로 시작할 수 없습니다.")
        }else {
            $.ajax({
                type : 'GET',

                url : '/member/idCheck',

                data : {
                    id : id
                },

                success : function(response){
                    idOverlap = response;

                    if(response){
                        alert("해당 아이디는 사용중입니다.\n" +
                            "다른 아이디를 입력해주세요!");
                    }else {
                        alert("사용가능한 아이디입니다.");
                    }
                },

                error : function(){
                    alert("에러 발생");
                }
            })
        }
    })
});


function emailDoubleCheck(){
    let emailCheck = $('input[name="emailCheck"]').val();

    if(emailCheck === emailChcekCode){
        return true;
    }else {
        return false;
    }
}





