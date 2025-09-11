document.getElementById("send").addEventListener("click", async function() {
    const name = document.getElementById("name").value;
    const email = document.getElementById("email-form").value;
    const question = document.getElementById("question").value;
    console.log(name)
    console.log(email)
    console.log(question)
    try {
        const response = await fetch("http://localhost:8080/api/data", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({name, email, question})
        });
        const text = await response.text();
        alert(text)
    } catch (e) {
        alert(e);
    }
})