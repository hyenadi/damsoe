document.addEventListener("DOMContentLoaded", () => {
    const tabButtons = document.querySelectorAll(".tab-btn");
    const tabContents = document.querySelectorAll(".tab-content");

    // 초기 설정
    tabContents.forEach(content => content.style.display = "none");
    document.getElementById("register").style.display = "block";

    tabButtons.forEach(btn => {
        btn.addEventListener("click", () => {
            // 탭 버튼 활성화 표시
            tabButtons.forEach(b => b.classList.remove("active"));
            btn.classList.add("active");

            // 탭 콘텐츠 표시
            const tabId = btn.dataset.tab;
            tabContents.forEach(content => {
                content.style.display = "none";
            });
            document.getElementById(tabId).style.display = "block";
        });
    });

    // 설문 폼/결과 조건부 렌더링
    const surveyForm = document.getElementById("surveyForm");
    const surveyResultDiv = document.getElementById("surveyResult");

    if (localStorage.getItem("accessToken")) {
        document.getElementById("surveyTab").disabled = false;

        fetch("/api/survey", {
            headers: {
                "Authorization": `Bearer ${localStorage.getItem("accessToken")}`
            }
        })
        .then(res => {
            if (!res.ok) throw new Error("NO_SURVEY");
            return res.json();
        })
        .then(data => {
            // 설문 데이터 있으면: 폼 숨기고 결과만 보여줌
            surveyForm.style.display = "none";
            surveyResultDiv.innerHTML = `
                <h3>저장된 설문 데이터</h3>
                <pre>${JSON.stringify(data, null, 2)}</pre>
            `;
        })
        .catch(err => {
            // 설문 데이터 없으면: 폼 보여주고 결과 영역 초기화
            surveyForm.style.display = "block";
            surveyResultDiv.innerHTML = "";
        });
    }
});
