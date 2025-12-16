document.addEventListener("DOMContentLoaded", () => {

    /* Typing Effect */
    const typing = document.getElementById("typing-text");
    const text = "Full Stack Java & Spring Boot Developer!";
    let i = 0;

    function type() {
        if (i < text.length) {
            typing.textContent += text.charAt(i);
            i++;
            setTimeout(type, 60);
        }
    }
    type();

    /* Reveal on Scroll */
    const timelineItems = document.querySelectorAll(".timeline-item");
    const experienceCards = document.querySelectorAll(".experience-card");

    const revealOnScroll = () => {
        const trigger = window.innerHeight * 0.85;

        timelineItems.forEach(item => {
            if (item.getBoundingClientRect().top < trigger) {
                item.classList.add("show");
            }
        });

        experienceCards.forEach(card => {
            if (card.getBoundingClientRect().top < trigger) {
                card.classList.add("show");
            }
        });
    };

    window.addEventListener("scroll", revealOnScroll);
    revealOnScroll();

    /* Contact Form */
    const form = document.getElementById("contactForm");
    if (form) {
        form.addEventListener("submit", e => {
            e.preventDefault();
            alert("Message Sent Successfully!");
            form.reset();
        });
    }

    /* About Project Button */
    const btn = document.getElementById("btn");
    if (btn) {
        btn.addEventListener("click", () => {
            window.location.href = "clean.html";
        });
    }

    /* Certificates Viewer */
    document.querySelectorAll(".view-cert-btn").forEach(button => {
        button.addEventListener("click", () => {
            const certFile = button.dataset.file;
            if(certFile) {
                window.open(`certificates/${certFile}`, "_blank");
            } else {
                alert("Certificate file not available!");
            }
        });
    });

});
