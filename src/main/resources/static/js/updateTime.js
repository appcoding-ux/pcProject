let token = $('input[name="_csrf"]').val();

let id = $('input[name="id"]').val();

$(function(){
    $('.logoutBtn').click(function(e){
        e.preventDefault();
        e.stopPropagation();

        if(confirm("컴퓨터 사용을 종료하고 로그아웃 하시겠습니까?")){
            $('#logoutForm').submit();
        }else {
            return false;
        }
    });

    setInterval(function(){updateTime(id)}, 60000);
})

function updateTime(id){
    $.ajax({
        type : 'post',
        url : '/updateTime',
        headers : {
            "X-CSRF-TOKEN" : token
        },
        data : {
            id : id
        },

        success : function(data){
            $('.memberTime').text(data);

            $('.remainingTimeText').text(data);
        },

        error : function(){
            console.log('에러 발생');
        }
    });
}