// script.js

document.addEventListener("DOMContentLoaded", function() {
    // Adiciona um listener ao formulário de login
    const loginForm = document.querySelector("form");
    
    if (loginForm) {
        loginForm.addEventListener("submit", function(event) {
            event.preventDefault(); // Impede o envio padrão do formulário

            const username = document.getElementById("username").value;
            const password = document.getElementById("password").value;

            // Simulando a autenticação
            // Aqui você deve chamar seu backend para autenticar de forma real
            if (username === "admin" && password === "password") {
                alert("Login bem-sucedido!");
                window.location.href = "/dashboard"; // Redireciona para uma página de dashboard
            } else {
                alert("Usuário ou senha incorretos!"); // Mensagem de erro
            }
        });
    }
});
