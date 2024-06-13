$(function(){
    let socket = new WebSocket("ws://192.168.0.114:8181/ws/chat");
    let roomId = $('input[name="roomId"]').val();
    console.log(socket.onmessage);

    socket.onopen = function(e){
        let adminOpenMsg = {
            "type" : "ADMIN_ENTER",
            "roomId" : roomId,
            "sender" : "admin",
            "message" : ""
        };

        socket.send(JSON.stringify(adminOpenMsg));
    }

    socket.onmessage = function(e) {
        let message = JSON.parse(e.data);

        if(message.type === "ADMIN_ENTER") return;

        if(message.sender === "admin") return;

        if(message.type === "OUT"){
            alert("회원님이 채팅을 종료하였습니다!");
            location.href = '/admin/qna';
        }

        $('.messages').append(`
                <div class="message new userMessage">
                    ${message.message}
                </div>
            `);

        scrollToBottom();
    };

    socket.onerror = function(e){
        console.log(e);
    }

    $('.adminMessage-submit').on('click', function(e){
        let message = $('textarea[name="chatMessage"]').val();

        if(message === ""){
            alert("메세지를 입력해주세요");
            return false;
        }else {
            adminChatSubmit(e, message);
        }
    });

    $('.message-input').on('keydown',function(e){
        if(e.key === "Enter" && !e.shiftKey){
            if(e.isComposing || e.keyCode === 229) return;
            let message = $(this).val();

            if(message === ""){
                alert("메세지를 입력해주세요");
                return false;
            }else {
                console.log('Enter키로 메세지 전송');
                e.preventDefault();
                e.stopPropagation();

                adminChatSubmit(e, message);
            }
        }
    });

    function adminChatSubmit(e, message){
        e.preventDefault();
        e.stopPropagation();

        displayChatMessage(message);

        let talkMsg = {
            "type" : "TALK",
            "roomId" : roomId,
            "sender" : "admin",
            "message" : message
        };

        socket.send(JSON.stringify(talkMsg));

        $('textarea[name="chatMessage"]').val('');

        scrollToBottom();
    }

    // 관리자가 메세지를 입력했을 때
    function displayChatMessage(message) {
        $('.messages').append(`
            <div class="message new admin">
                ${message}
            </div>
        `)
    }

    function scrollToBottom() {
        let chatBox = $('.messages');
        chatBox.scrollTop(chatBox[0].scrollHeight);
    }
})