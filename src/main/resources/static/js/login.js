document.getElementById("loginForm").addEventListener("submit", async function (e) {
    e.preventDefault();

    const email = document.getElementById("loginEmail").value;
    const password = document.getElementById("loginPassword").value;
    const tel = document.getElementById("loginTel").value;

    try {
        const res = await fetch("/api/auth/login", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({email, password, tel})
        });

        const resultDiv = document.getElementById("loginResult");

        if (res.ok) {
            const userInfo = await res.json();
            resultDiv.textContent = `로그인 성공! 사용자 ID: ${userInfo.userId} 사용자 email: ${userInfo.email}`;
            resultDiv.style.color = "green";

            // 로그인 성공 시 채팅 탭 활성화
            document.querySelector("[data-tab='chat']").disabled = false;
            document.querySelector("[data-tab='chat']").classList.remove("disabled");
        } else {
            const error = await res.text();
            resultDiv.textContent = `로그인 실패: ${error}`;
            resultDiv.style.color = "red";
        }
    } catch (err) {
        document.getElementById("loginResult").textContent = "에러: " + err.message;
    }
});
