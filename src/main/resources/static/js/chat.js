$(function(){
    let token = $('input[name="_csrf"]').val();
    let id = $('input[name="id"]').val();
    let socket;
    let roomId;
    let outMsg={"type" : "OUT","roomId":roomId ,"sender":id,"msg":""};

    $('.chatBtn').click(function(e){
        e.preventDefault();
        e.stopPropagation();

        $(this).stop().hide();

        $('.chatBox').stop().show();

        $.ajax({
            type : 'post',
            url : '/chat/createRoom',
            headers : {
                "X-CSRF-TOKEN": token
            },
            data : {
                name : id
            },

            success : function(data){
                roomId = data.roomId;
                console.log(data);

                $.ajax({
                    type : 'get',
                    url : '/chat/chatRoom/' + roomId,
                    success : function(data){
                        console.log(data);

                        socket = new WebSocket("ws://192.168.0.114:8181/ws/chat");

                        // WebSocket 연결이 열린 후에 방 입장을 시도합니다.
                        socket.onopen = function() {
                            console.log("open chat");
                            enterRoom(socket, data.roomId, id);
                        };

                        socket.onmessage = handleTextMessage;

                        socket.onerror = function(error) {
                            console.log('WebSocket Error: ', error);
                        };
                    },

                    error : function(error){
                        console.log(error);
                    }
                })
            },

            error : function(error){
                console.log(error);
            }
        });
    });

    $('.message-submit').on('click', function(e){
        let message = $('textarea[name="chatMessage"]').val();

        if(message === ""){
            alert("메세지를 입력해주세요");
            return false;
        }else {
            e.stopPropagation();
            e.preventDefault();

            console.log(message);

            chatSubmit(e, message);
        }
    });


    $('#myTextarea').on('keydown', function(e){
        if(e.key === "Enter" && !e.shiftKey){
            if(e.isComposing || e.keyCode === 229) return;
            console.log("Enter 키보드 키로 전송");

            let message = $(this).val();

            if(message === ""){
                alert("메세지를 입력해주세요");
                return false;
            }else {
                e.stopPropagation();
                e.preventDefault();


                console.log(message);

                chatSubmit(e, message);
            }

        }
    });


    // 채팅방 닫기 (웹소켓 close()함수 실행)
    $('.chatClose').click(function(e){
        e.preventDefault();
        e.stopPropagation();

        if(confirm("채팅을 종료하시겠습니까?")){
            chatClose(socket);
        }else {
            return false;
        }
    })

    function enterRoom(socket, roomId, id){
        let enterMsg = {
            "type" : "ENTER",
            "roomId" : roomId,
            "sender" : id,
            "message" : ""
        }
        socket.send(JSON.stringify(enterMsg));
    }

    function handleTextMessage(event) {
        let message = JSON.parse(event.data);
        console.log("Received message:", message);

        switch (message.type) {
            case "TALK":
                displayChatMessage(message.sender, message.message);
                break;
            case "ENTER":
                // displaySystemMessage();
                break;
            case "ADMIN_ENTER" :
                displaySystemMessage();
                break;
            default:
                console.log("Unknown message type:", message.type);
        }
    }

    function chatSubmit(e, message){
        console.log('채팅 전송 메서드 실행')
        e.preventDefault();
        e.stopPropagation();

        $('.messages').append(`
                <div class="message new userMessage">
                    ${message}
                </div>
        `);

        let talkMsg = {
            "type" : "TALK",
            "roomId" : roomId,
            "sender" : id,
            "message" : message
        };

        socket.send(JSON.stringify(talkMsg));

        $('textarea[name="chatMessage"]').val('');

        scrollToBottom();

        setTimeout(() => {
            chatClose(socket);
        }, 120000);
    }

    // 관리자가 메세지를 전송했을 때와 내가 메세지를 전송했을 떄
    function displayChatMessage(sender, message) {
        console.log('채팅방에 쓴 메세지 보여주기', message);
        if(sender === "admin"){
            $('.messages').append(`
                <div class="message new admin">
                    ${message}
                </div>
            `);
            scrollToBottom();
        }
    }

    // 채팅에 연결이 되었을 때 실행되는 함수
    function displaySystemMessage() {

        let messages = $('.messages');

        // 처음 채팅방에 입장했을 때
        console.log('관리자 입장 완료 ! ');

        messages.children().remove();

        messages.append(`
            <div class="chatStart">
                <span class="chatStartText">관리자와 연결되었습니다.</span>
            </div>
            <div class="message new admin">
                무엇을 도와드릴까요?
            </div>
        `);

        $('.message-input').removeAttr("readonly");

        $('.message-submit').css("pointer-events","auto");
    }

    function scrollToBottom() {
        let chatBox = $('.messages');
        chatBox.scrollTop(chatBox[0].scrollHeight);
    }

    function chatClose(socket){
        let outMsg={"type" : "OUT","roomId":roomId ,"sender":id,"msg":""};
        socket.send(JSON.stringify(outMsg));
        socket.close();

        console.log('연결 종료');
        $('.messages').children().remove();

        $('.chatBox').stop().hide();

        $('.chatBtn').stop().show();
    }

    //메세지 보내기 버튼 눌렀을 떄..
    // function sendMsg() {
    //     let content=document.querySelector('.content').value;
    //     var talkMsg={"type" : "TALK","roomId":[[${room.roomId}]] ,"sender":"chee","msg":content};
    //     socket.send(JSON.stringify(talkMsg));
    // }


    // 채팅 종료
    // function quit(){
    //     var quitMsg={"type" : "QUIT","roomId":[[${room.roomId}]] ,"sender":"chee","msg":""};
    //     socket.send(JSON.stringify(quitMsg));
    //     socket.close();
    //     location.reload();
    // }
});