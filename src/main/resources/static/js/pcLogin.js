$(function(){
    let token = $('input[name="_csrf"]').val();

    let id = $('input[name="id"]').val();

    console.log(token);

    $('.seatBtn').click(function(e){
        e.preventDefault();
        e.stopPropagation();

        let seat = $(this).children('.seatBtnText').text();

        if(confirm(seat + "번 자리에 로그인하시겠습니까?")){
            $('#pcLoginForm').append(`<input type='hidden' name='seat' value='${seat}' />`).submit();
        }else {
            return false;
        }
    });

    let frag = false;

    $('.paymentBtn').click(function(e){
        if(!frag){
            $('.paymentWrap').stop().slideDown("fast");
            frag = true;
        }else {
            $('.paymentWrap').stop().slideUp("fast");
            frag = false;
            $('.chargingBox').html("<span>요금제를 선택해주세요</span>");
            $('.buyPayment').css({"background" : "#c2c2c2", "color" : "#fff", "pointer-events" : "none"});
        }

    });

    $('.closeBtn').click(function(e){
        $('.paymentWrap').stop().slideUp("fast");
        $('.chargingBox').html("<span>요금제를 선택해주세요</span>");
        $('.buyPayment').css({"background" : "#c2c2c2", "color" : "#fff", "pointer-events" : "none"});
        frag = false;
    });

    $('.selectPayment').click(function(){
        let paymentNum = $(this).find('input[name="num"]').val();
        let paymentName = $(this).find("p").text();
        let paymentTime = $(this).find('.pay_time').text();
        let paymentPrice = $(this).find('.pay_price').text();

        let str = `<p style="border:1px solid #ccc; border-radius: 10px; text-align: center; font-size:14px; color: #888; background:#fff; padding:2px 4px; font-weight:600; ">${paymentName}</p>
                          <span style="color:#000; font-weight:bold; font-size:18px;">시간 : ${paymentTime}</span>
                          <span style="color:#000; font-weight:bold; font-size:18px;">가격 : ${paymentPrice}</span>
                          <input type="hidden" name="num" value="${paymentNum}" />`;
        $('.chargingBox').html(str).css({"flex-direction" : "column", "justify-content" : "space-evenly"});

        $('.buyPayment').css({"pointer-events" : "auto", "background": "yellow", "color" : "#000"});
    });

    $('.buyPayment').click(function(e){
        e.preventDefault();
        e.stopPropagation();

        let num = $('.chargingBox').find('input[name="num"]').val();
        let id = $('input[name="id"]').val();
        let email = $('input[name="email"]').val();
        let name = $('input[name="name"]').val();
        let phone = $('input[name="phone"]').val();

        IMP.init('imp21466554');

        IMP.request_pay({
            pg: 'inicis', // version 1.1.0부터 지원.
            pay_method: 'card',
            merchant_uid: 'merchant_' + new Date().getTime(),
            name: '피시방 : 요금결제',
            amount: 100, //판매 가격
            buyer_email: email,
            buyer_name: name,
            buyer_tel: phone,
            buyer_addr: '',
            buyer_postcode: ''
        },function (rsp){
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
                        url : '/addTime',
                        headers : {
                            "X-CSRF-TOKEN" : token
                        },
                        data : {
                            num : num,
                            id : id
                        },
                        success : function(data) {
                            console.log("성공적으로 시간 추가됨");

                            alert("결제가 완료되었습니다. \n남은 시간을 확인해주세요!");

                            console.log(data);

                            $('.memberTime').text(data);

                            $('.remainingTimeText').text(data);
                        }, error : function(error){
                            console.log("시간 추가 실패")
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
});