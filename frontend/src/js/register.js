document.getElementById("btn-register").addEventListener("click", async function() {
    const name = document.getElementById("username").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    try {
        const response = await fetch("http://localhost:8080/api/reg", {
            method: "POST",
                headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({name, email, password})
        })
    } catch (e) {
        console.log(e);
    }
})
