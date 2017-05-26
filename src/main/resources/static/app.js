var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    } else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/mqtt-spring-boot/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/user/topic/greetings', function (msg) {
            showGreeting(JSON.parse(msg.body).destination, JSON.parse(msg.body).content);
        });
    });
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
}

function sendContent() {
    stompClient.send("/app/toMqtt", {}, JSON.stringify({'content': $("#content").val(), 'destination': $("#destination").val()}));
}

function showGreeting(destination, content) {
    var objJSON = JSON.parse(content);
    $("#greetings").append("<tr><td>" + objJSON.destination + "</td><td>" + objJSON.content + "</td></tr>");
}

$(function () {
    connect();

    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#connect").click(function () {
        connect();
    });
    $("#disconnect").click(function () {
        disconnect();
    });
    $("#send").click(function () {
        sendName();
    });
    $("#send-content").click(function () {
        sendContent();
    });
});