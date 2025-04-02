const VALID_MBTI = [
    "INTJ", "INTP", "ENTJ", "ENTP",
    "INFJ", "INFP", "ENFJ", "ENFP",
    "ISTJ", "ISFJ", "ESTJ", "ESFJ",
    "ISTP", "ISFP", "ESTP", "ESFP"
];

// 체크박스 값 배열로 추출하는 함수
const getCheckedValues = (name) => {
    return Array.from(document.querySelectorAll(`input[name="${name}"]:checked`)).map(el => el.value);
};

document.getElementById("surveyForm").addEventListener("submit", async function (e) {
    e.preventDefault();

    const accessToken = localStorage.getItem("accessToken");
    if (!accessToken) {
        alert("로그인이 필요합니다.");
        return;
    }

    const mbtiInput = document.getElementById("mbti").value.trim().toUpperCase();
    if (!VALID_MBTI.includes(mbtiInput)) {
        alert("유효한 MBTI 형식을 입력해주세요. (예: INFP)");
        return;
    }

    const payload = {
        mbti: mbtiInput,
        keywords: getCheckedValues("keywords"),
        speakingStyle: document.getElementById("speakingStyle").value,
        hobbies: getCheckedValues("hobbies"),
        loveStyle: getCheckedValues("loveStyle"),
        sensitiveTopics: getCheckedValues("sensitiveTopics"),
        emotionStyle: document.getElementById("emotionStyle").value,
        speechSpeed: document.getElementById("speechSpeed").value
    };

    try {
        const res = await fetch("/api/survey", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${accessToken}`
            },
            body: JSON.stringify(payload)
        });

        const resultDiv = document.getElementById("surveyResult");

        if (res.ok) {
            const result = await res.json();
            resultDiv.textContent = result.message;
            resultDiv.style.color = "green";
        } else {
            const error = await res.text();
            resultDiv.textContent = `실패: ${error}`;
            resultDiv.style.color = "red";
        }
    } catch (err) {
        document.getElementById("surveyResult").textContent = "에러: " + err.message;
    }
});
