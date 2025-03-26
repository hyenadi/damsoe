/ 메시지 보내기 버튼 동작 (JWT 토큰 필요)
document.getElementById("sendMessageBtn").addEventListener("click", async function () {
    const accessToken = localStorage.getItem("accessToken");
    const chatResult = document.getElementById("chatResult");

    if (!accessToken) {
        chatResult.textContent = "로그인이 필요합니다.";
        chatResult.style.color = "red";
        return;
    }

    try {
        const res = await fetch("/api/chat/send", { // 가정된 보호된 엔드포인트
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${accessToken}`
            },
            body: JSON.stringify({ message: "테스트 메시지" })
        });

        if (res.ok) {
            const data = await res.json();
            chatResult.textContent = `메시지 전송 성공: ${data.message}`;
            chatResult.style.color = "green";
        } else {
            const error = await res.text();
            chatResult.textContent = `전송 실패: ${error}`;
            chatResult.style.color = "red";
        }
    } catch (err) {
        chatResult.textContent = "에러: " + err.message;
    }
});