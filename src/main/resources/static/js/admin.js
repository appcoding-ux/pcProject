$(function(){
    $('.adminLogout').click(function(){
        if(confirm("로그아웃 하시겠습니까?")){
            location.href = '/logout';
        }else {
            return false;
        }
    });

    $('.memberPage').click(function(e){
        e.preventDefault();

        let num = $(this).find("a").data("num");

        $('#memberForm').append(`<input type="hidden" name="page" value="${num}" />`).submit();
    });

    $('.foodInsert').click(function(){
        let options = 'width=840,height=1040,top=100%,left=' + (window.innerWidth / 2 - 400);

        window.open('/food/insertPage', '_blank',options);
    });

    $('.foodSave').click(function(e){
        e.preventDefault();
        e.stopPropagation();

        let foodName = $('input[name="foodName"]');
        let foodContent = $('#foodContent');
        let price = $('#price');
        let category = $('input[name="category"]');
        let foodImage = $('input[name="foodImage"]');

        if(foodName.val() === ""){
            alert("음식 이름을 입력해주세요");
            foodName.focus();
            return false;
        }else if(foodContent.val() === ""){
            alert("음식 설명을 적어주세요");
            foodContent.focus();
            return false;
        }else if(price.val() === "" || parseInt(price.val()) < 0 || parseInt(price.val()) === 0){
            alert("음식 가격을 적어주시고 음수와 0원을 불가합니다.");
            price.focus();
            return false;
        }else if(!category.is(":checked")) {
            alert('음식 카테고리를 체크해주세요');
            category.focus();
            return false;
        }else if(foodImage.val() === ""){
            alert('음식 사진은 필수입니다.');
            foodImage.focus();
            return false;
        }else {
            $('#foodInsertForm').submit();
        }
    });

    $('.foodPage').click(function(e){
        e.preventDefault();

        let num = $(this).find("a").data("num");

        $('#foodForm').append(`<input type="hidden" name="page" value="${num}" />`).submit();
    });

    let frag = false;

    $('#select-button').click(function(){
        if(frag){
            $('#options').stop().slideUp();

            $('#chevrons i').css("color", "#d1dede");

            $('#option-bg').hide();

            $('.label').hide();

            frag = false;
        }else {
            $('#options').stop().slideDown();

            $('#chevrons i').css("color", "#2d3667");

            $('.label').css({"padding":"12px 14px", "display" : "block"});

            $('#option-bg').show();

            frag = true;
        }
    });

    $('.option').click(function(){
        let category = $(this).find(".label").text();

        $('#selected-value').find("span").text(category);

        $(this).parent('#options').stop().slideUp();

        $('#chevrons i').css("color", "#d1dede");

        $('#option-bg').hide();

        $('.label').hide();

        frag = false;
    });

    $('.forceUserDelete').click(function(){
        let id = $(this).siblings(".userId").text();

        if(confirm('해당 회원을 강제로 삭제하시겠습니까?')){
            $('#memberForm').append(`<input type='hidden' name='id' value='${id}' />`).attr({'action' : '/member/forceRemove', 'method' : 'post'}).submit();
        }
    })
});

function addImage(img){
    if(img.files && img.files[0]){
        let reader = new FileReader();
        reader.onload = function(e){
            console.log('실행');
            let imgSrc = e.target.result;

            $(img).siblings('.foodImage').html(`
                <img src='${imgSrc}' alt="음식 사진" width="40%" height="100%"/>`);
        }
        reader.readAsDataURL(img.files[0]);
    }
}