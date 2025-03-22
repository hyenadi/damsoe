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
});
