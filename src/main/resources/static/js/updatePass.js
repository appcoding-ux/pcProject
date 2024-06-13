$(function(){
    $('.updatePassBtn').click(function(e){
        e.preventDefault();

        let pass = $('input[name="password"]').val();
        let passCheck = $('input[name="passwordCheck"]').val();

        if(pass === "" || passCheck === ""){
            alert("비밀번호 또는 비밀번호 확인을 입력해주세요");
            return false;
        }else {
            if(pass === passCheck){
                $('#updatePassForm').submit();
            }else {
                alert("비밀번호가 일치하지 않습니다 \n다시 입력해주세요!");
                return false;
            }
        }
    })
})