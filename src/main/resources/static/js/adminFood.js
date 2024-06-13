$(function(){
    $('.card').click(function(){
        let foodNum = $(this).find("img").attr("alt");

        let options = 'width=840,height=1040,top=100%,left=' + (window.innerWidth / 2 - 400);

        window.open(`/food/read/${foodNum}`, '_blank', options);
    });

    let frag = true;
    $('.foodUpdate').click(function(e){
        if(frag){
            if(confirm("상품을 수정하시겠습니까?")){
                frag = false;
                $('.foodImageBox').prepend(`<input id="foodImage" name="foodImage" type="file" class="sr-only" onchange="addImage(this)" />`);

                $('.foodImage').find("img").remove();

                $('.foodImage').append(`
                <div class="text-center">
                    <svg class="mx-auto h-12 w-12 text-gray-300" viewBox="0 0 24 24" fill="currentColor" aria-hidden="true">
                        <path fill-rule="evenodd" d="M1.5 6a2.25 2.25 0 012.25-2.25h16.5A2.25 2.25 0 0122.5 6v12a2.25 2.25 0 01-2.25 2.25H3.75A2.25 2.25 0 011.5 18V6zM3 16.06V18c0 .414.336.75.75.75h16.5A.75.75 0 0021 18v-1.94l-2.69-2.689a1.5 1.5 0 00-2.12 0l-.88.879.97.97a.75.75 0 11-1.06 1.06l-5.16-5.159a1.5 1.5 0 00-2.12 0L3 16.061zm10.125-7.81a1.125 1.125 0 112.25 0 1.125 1.125 0 01-2.25 0z" clip-rule="evenodd" />
                    </svg>
                    <div class="mt-4 flex text-sm leading-6 text-gray-600">
                        <label for="foodImage" class="relative cursor-pointer rounded-md bg-white font-semibold text-indigo-600 focus-within:outline-none focus-within:ring-2 focus-within:ring-indigo-600 focus-within:ring-offset-2 hover:text-indigo-500">
                            <span>Upload a file</span>
                        </label>
                        <p class="pl-1">or drag and drop</p>
                    </div>
                    <p class="text-xs leading-5 text-gray-600">PNG, JPG, JPEG up to 10MB</p>
                </div>`
                );

                $('.foodDTO').removeAttr('readonly');
            }
        }else {
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
                $('#foodUpdateForm').submit();
            }
        }
    });

    $('.foodDelete').click(function(){
        if(confirm("상품을 삭제하시겠습니까?")){
            $('#foodUpdateForm').attr('action', '/food/remove').submit();
        }
    });
});