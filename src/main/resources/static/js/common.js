$(function(){
    let id = $('input[name="id"]').val();
    let email = $('input[name="email"]').val();
    let name = $('input[name="name"]').val();
    let phone = $('input[name="phone"]').val();
    let token = $('input[name="_csrf"]').val();

    $('.indexLogoutBtn').click(function(e){
        e.preventDefault();
        e.stopPropagation();

        if(confirm("컴퓨터 사용을 종료하고 로그아웃 하시겠습니까?")){
            location.href = ' https://kauth.kakao.com/oauth/logout?client_id=4f8fd0ea2b58d54fc209c75db615c0e7&logout_redirect_uri=http://localhost:8181/logout';
        }else {
            return false;
        }
    });

    $('.trigger').on('click', function() {
        $('.modal-wrapper').toggleClass('open');
        $('.page-wrapper').toggleClass('blur-it');
        return false;
    });

    $('.categoryMenu').click(function(e){
        e.preventDefault();
        e.stopPropagation();

        let category = $(this).attr("href");

        console.log(category);

        $.ajax({
            type : 'get',
            url : '/food/'+category,

            success : function(data){
                let foodBox = $('.foodModalLeftContent > ul');
                foodBox.children("li").remove();

                for(let food of data){

                    foodBox.append(`
                        <li class="leftContentBox" data-num="${food.foodNum}">
                            <div class="leftContentImg">
                                <img src="/food/view/${food.fileName}" alt="">
                            </div>

                            <div class="leftContentText">
                                <p>${food.foodName}</p>
                                <p data-price="${food.price}">${formatCurrency(food.price)}원</p> 
                            </div>
                        </li>
                    `);
                }
            },

            error : function(error){
                console.log(error);
            }
        });
    });

    // 동적으로 추가된 .leftContentBox 요소에 클릭 이벤트 바인딩
    $(document).on('click', '.leftContentBox', function() {
        let foodNum = $(this).data("num");
        let foodName = $(this).find('.leftContentText').children("p:first-child").text();
        let price = $(this).find('.leftContentText').children('p:last-child').data('price');

        let selectFood = $('.memberSelectFood');

        console.log(foodNum, foodName, price);

        // 음식을 눌렀을 때 이미 포함된 음식인지 체크
        if ($('.rightContentTopFoodBox').children().length === 0) {
            selectFoodAdd(foodNum, foodName, price);
        } else {
            let found = false;
            for (let i = 0; i < selectFood.length; i++) {
                let key = $(selectFood[i]).find('input[name="foodNum"]').val();
                let value = $(selectFood[i]).find('input[name="foodName"]').val();

                console.log(key);
                console.log(foodNum);
                console.log(value);

                // 이미 있는 음식이면 수량 증가
                if (parseInt(key) === foodNum) {
                    console.log('원래 있던 음식');
                    selectFoodPlus($(selectFood[i]).find('.selectFoodAmountPlus'));
                    found = true;
                    return;
                }
            }

            // 새로운 음식이면 추가
            if (!found) {
                console.log('없던 음식');
                selectFoodAdd(foodNum, foodName, price);
            }
        }

        let memberSelectTotalPrice = parseInt($('input[name="memberSelectTotalPrice"]').val());

        memberSelectTotalPrice += price;

        $('input[name="memberSelectTotalPrice"]').val(memberSelectTotalPrice);

        $('.totalPrice').text(formatCurrency(memberSelectTotalPrice) + "원");

        $('.foodOrderBtn').css({"pointer-events": "auto", "background": "yellow", "color": "#000"});
    });

    $('.foodOrderBtn').click(function(e){
        e.preventDefault();
        e.stopPropagation();

        let memberSelectFood = {};

        let selectFood = $('.memberSelectFood');

        let orderRequest = $('input[name="orderRequest"]').val();

        let memberSelectTotalPrice = $('input[name="memberSelectTotalPrice"]').val();

        for(let i = 0; i < selectFood.length; i++){
            let key = $(selectFood[i]).find('input[name="foodNum"]').val();
            let value = $(selectFood[i]).find('input[name="foodAmount"]').val();

            memberSelectFood[key] = value;
        }

        IMP.init('imp21466554');

        IMP.request_pay({
            pg: 'inicis', // version 1.1.0부터 지원.
            pay_method: 'card',
            merchant_uid: 'merchant_' + new Date().getTime(),
            name: '피시방 : 음식결제',
            amount: 100, //판매 가격
            buyer_email: email,
            buyer_name: name,
            buyer_tel: phone,
            buyer_addr: '',
            buyer_postcode: ''
        }, function(rsp){
            console.log(rsp);

            $.ajax({
                type : 'post',
                headers : {
                    "X-CSRF-TOKEN" : token
                },
                url : '/verify/'+rsp.imp_uid
            }).done(function(data){
                if(rsp.paid_amount === data.response.amount){
                    console.log('결제 및 결제검증 완료 - 결제가 완료되었습니다.');

                    $.ajax({
                        type : 'post',
                        url : '/orderFood',
                        headers : {
                            "X-CSRF-TOKEN" : token
                        },
                        data : {
                            memberSelectFood : memberSelectFood,
                            orderRequest : orderRequest,
                            totalPrice : memberSelectTotalPrice,
                            id : id
                        },
                        success : function(data) {
                            if(data === 'success'){
                                console.log("성공적으로 음식 주문됨");

                                alert("결제가 완료되었습니다. \n조금만 기다려주세요!");

                                $('.rightContentTopFoodBox').children().remove();

                                $('.modal-wrapper').toggleClass('open');
                                $('.page-wrapper').toggleClass('blur-it');

                                $('.totalPrice').text("0원");

                                $('input[name="memberSelectTotalPrice"]').val(0);
                            }

                        }, error : function(error){
                            console.log("음식 주문 실패")
                        }
                    });
                }else {
                    let msg = '결제에 실패했습니다.';
                    msg += '에러 내용 :' + rsp.error_msg;
                    alert(msg);
                }
            }).error(function(error){
                console.log(error);
            });
        });
    });

    $('.foodModalSearchInput > input').focusout(function(){
        let keyword = $(this).val();
        
        if(keyword === ''){
            keyword = '전체';
        }
        
        console.log(keyword)

        $.ajax({
            type : 'get',
            url : '/food/search/'+keyword,
            success : function(data){
                let foodBox = $('.foodModalLeftContent > ul');
                foodBox.children("li").remove();

                for(let food of data){

                    foodBox.append(`
                        <li class="leftContentBox" data-num="${food.foodNum}">
                            <div class="leftContentImg">
                                <img src="/food/view/${food.fileName}" alt="">
                            </div>

                            <div class="leftContentText">
                                <p>${food.foodName}</p>
                                <p data-price="${food.price}">${formatCurrency(food.price)}원</p>
                            </div>
                        </li>
                    `);
                }
            }, error : function(error){
                console.log(error);
            }
        });
    });
});


const indicator = document.querySelector('.nav-indicator');
const items = document.querySelectorAll('.nav-item');

function handleIndicator(el) {
    items.forEach(item => {
        item.classList.remove('is-active');
        item.removeAttribute('style');
    });

    indicator.style.width = `${el.offsetWidth}px`;
    indicator.style.left = `${el.offsetLeft}px`;
    indicator.style.backgroundColor = el.getAttribute('active-color');

    el.classList.add('is-active');
    el.style.color = el.getAttribute('active-color');
}


items.forEach((item, index) => {
    item.addEventListener('click', e => {handleIndicator(e.target);});
    item.classList.contains('is-active') && handleIndicator(item);
});

function selectFoodAdd(foodNum, foodName, price){
    $('.rightContentTopFoodBox').append(`
        <div class="memberSelectFood">
            <input type="hidden" name="foodNum" value="${foodNum}" />
            <input type="hidden" name="foodName" value="${foodName}" />
            <p>${foodName}</p>

            <button type="button" class="selectFoodDelete" onclick="selectFoodRemove(this)"><i class="fa-solid fa-x"></i></button>

            <div class="selectFoodAmount">
                <input type="hidden" name="price" value="${price}" />
                <input type="hidden" name="totalPrice" value="${price}" />
                <button type="button" class="selectFoodAmountMinus" onclick="selectFoodMinus(this)">-</button>
                <label for="foodAmount" class="foodAmountLabel">1</label>
                <input type="number" name="foodAmount" value="1" id="foodAmount"/>
                <button type="button" class="selectFoodAmountPlus" onclick="selectFoodPlus(this)">+</button>
            </div>

            <span class="selectFoodPrice">${formatCurrency(price)}원</span>
        </div>
    `);
}

// 숫자 포맷팅할 함수
function formatCurrency(amount, currency = 'KRW') {
    return new Intl.NumberFormat('ko-KR', {
        style: 'currency',
        currency: currency
    }).format(amount);
}

function selectFoodMinus(btn){
    let foodAmount = parseInt($(btn).siblings('#foodAmount').val());
    let price = parseInt($(btn).siblings('input[name="price"]').val());
    let totalPrice = parseInt($(btn).siblings('input[name="totalPrice"]').val());

    let memberSelectTotalPrice = parseInt($('input[name="memberSelectTotalPrice"]').val());

    if(foodAmount === 1){
        alert('최소 음식 수량은 1개입니다.');
    }else {
        foodAmount -= 1

        totalPrice -= price;

        memberSelectTotalPrice -= price;

        $(btn).siblings('input[name="totalPrice"]').val(totalPrice);

        $('input[name="memberSelectTotalPrice"]').val(memberSelectTotalPrice);

        $(btn).siblings('#foodAmount').val(foodAmount);

        $(btn).siblings('.foodAmountLabel').text(foodAmount);

        $(btn).parent('.selectFoodAmount').siblings('.selectFoodPrice').text(formatCurrency(totalPrice)+"원");

        $('.totalPrice').text(formatCurrency(memberSelectTotalPrice)+"원");
    }
}

function selectFoodPlus(btn){
    let foodAmount = parseInt($(btn).siblings('#foodAmount').val());
    let price = parseInt($(btn).siblings('input[name="price"]').val());
    let totalPrice = parseInt($(btn).siblings('input[name="totalPrice"]').val());

    let memberSelectTotalPrice = parseInt($('input[name="memberSelectTotalPrice"]').val());

    memberSelectTotalPrice += price;

    foodAmount += 1;

    totalPrice += price;

    $('input[name="memberSelectTotalPrice"]').val(memberSelectTotalPrice);

    $(btn).siblings('input[name="totalPrice"]').val(totalPrice);

    $(btn).siblings('#foodAmount').val(foodAmount);

    $(btn).siblings('.foodAmountLabel').text(foodAmount);

    $(btn).parent('.selectFoodAmount').siblings('.selectFoodPrice').text(formatCurrency(totalPrice)+"원");

    $('.totalPrice').text(formatCurrency(memberSelectTotalPrice)+"원");
}

function selectFoodRemove(btn){
    let selectFoodPrice = parseInt($(btn).siblings('.selectFoodAmount').children('input[name="totalPrice"]').val());

    let memberSelectTotalPrice = parseInt($('input[name="memberSelectTotalPrice"]').val());

    memberSelectTotalPrice -= selectFoodPrice;

    $('input[name="memberSelectTotalPrice"]').val(memberSelectTotalPrice);

    $('.totalPrice').text(formatCurrency(memberSelectTotalPrice)+"원");

    $(btn).parent('.memberSelectFood').remove();

    if($('.rightContentTopFoodBox').children().length === 0){
        $('.foodOrderBtn').css({"pointer-events" : "none", "background": "#c2c2c2", "color" : "#fff"});
    }
}
