document.getElementById("registerForm").addEventListener("submit", async function (e) {
    e.preventDefault();

    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const tel = document.getElementById("tel").value;
    const name = document.getElementById("name").value;
    const gender = document.querySelector('input[name="gender"]:checked').value;
    const birthDate = document.getElementById("birthDate").value;
    const location = document.getElementById("location").value;
    const education = document.getElementById("education").value;

    try {
        const res = await fetch("/api/auth/register", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({email, password, tel, name, gender, birthDate, location, education})
        });

        const resultDiv = document.getElementById("result");

        if (res.ok) {
            const userId = await res.text();
            resultDiv.textContent = `가입 성공! 사용자 ID: ${userId}`;
            resultDiv.style.color = "green";
        } else {
            const error = await res.text();
            resultDiv.textContent = `가입 실패: ${error}`;
            resultDiv.style.color = "red";
        }
    } catch (err) {
        document.getElementById("result").textContent = "에러: " + err.message;
    }
});