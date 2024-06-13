$(function(){
    $('.logoutBtn').click(function(e){
        e.preventDefault();
        e.stopPropagation();

        if(confirm("컴퓨터 사용을 종료하고 로그아웃 하시겠습니까?")){
            location.href = '/logout';
        }else {
            return false;
        }
    });
});