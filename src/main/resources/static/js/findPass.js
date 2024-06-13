$(function(){
    let code;

    const emailRegex = /[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,3}/;

    $('.findPassBtn').click(function(e){
        e.preventDefault();

        let email = $(this).siblings('input[name="email"]').val();

        if(email === ""){
            alert('이메일을 입력해주세요');
            return false;
        }else if(!emailRegex.test(email)){
            alert('올바른 이메일 형식을 입력해주세요!');
            return false;
        }else {
            $.ajax({
                type : 'get',
                url : '/mail/findPass',
                data : {
                    email : email
                },

                success : function(data){
                    code = parseInt(data);
                    alert('인증번호가 전송되었습니다.');
                },

                error : function(error){
                    console.log(error);
                }
            })
        }
    });

    $('.findPassCheckBtn').click(function(e){
        e.preventDefault();

        let authCode = $(this).siblings('input[name="emailCode"]').val();

        if(authCode === ""){
            alert("이메일 인증 코드를 입력해주세요.");
            return false;
        }else {
            if(parseInt(authCode) === code){
                alert('인증되었습니다. \n비밀번호 변경 페이지로 이동합니다.');
                $('#findPassForm').submit();
            }else {
                return false;
            }
        }
    })
})