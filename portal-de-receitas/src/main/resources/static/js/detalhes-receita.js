document.addEventListener("DOMContentLoaded", function () {
    const recipeId = window.location.pathname.split("/").pop();
    const recipeContainer = document.getElementById("recipe-container");

    let isLoggedIn = false; // Variável para armazenar o status de autenticação

    // Verificar se o usuário está autenticado
    async function checkAuthentication() {
        try {
            const response = await fetch("/api/usuarios/autenticado");
            const authenticated = await response.json();
            isLoggedIn = authenticated;
        } catch (error) {
            console.error("Erro ao verificar autenticação:", error);
        }
    }

    // Carregar os detalhes da receita
    async function loadRecipeDetails() {
        try {
            const response = await fetch(`/api/receitas/${recipeId}/detalhes`);
            if (!response.ok) {
                throw new Error("Erro ao carregar detalhes da receita");
            }
            const recipe = await response.json();
            displayRecipeDetails(recipe);
        } catch (error) {
            console.error("Erro ao carregar os detalhes da receita:", error);
            recipeContainer.innerHTML = "<p>Erro ao carregar os detalhes da receita. Tente novamente mais tarde.</p>";
        }
    }

    // Exibir os detalhes da receita
    function displayRecipeDetails(recipe) {
        let actions = "";

        if (isLoggedIn) {
            actions = `
                <div class="recipe-actions">
                    <button onclick="editRecipe(${recipe.id})">Editar Receita</button>
                    <button onclick="deleteRecipe(${recipe.id})">Excluir Receita</button>
                </div>
            `;
        }

        recipeContainer.innerHTML = `
            <div class="recipe-header">
                <h1>${recipe.nome}</h1>
                <p>${recipe.descricao}</p>
            </div>
            <div class="recipe-details">
                <div class="recipe-section">
                    <h2>Ingredientes</h2>
                    <ul>
                        ${recipe.ingredientes
                            .map(ingrediente => `<li>${ingrediente.quantidade} ${ingrediente.unidadeMedida} de ${ingrediente.nome}</li>`)
                            .join("")}
                    </ul>
                </div>
                <div class="recipe-section">
                    <h2>Modo de Preparo</h2>
                    <ol>
                        ${recipe.passos
                            .sort((a, b) => a.ordem - b.ordem)
                            .map(passo => `<li>${passo.descricao}</li>`)
                            .join("")}
                    </ol>
                </div>
                ${actions}
            </div>
        `;
    }

    // Função para editar receita
    function editRecipe(recipeId) {
        window.location.href = `/editar-receita/${recipeId}`;
    }

    // Função para excluir receita
    function deleteRecipe(recipeId) {
        if (confirm("Tem certeza que deseja excluir esta receita?")) {
            fetch(`/api/receitas/${recipeId}`, {
                method: "DELETE",
            })
                .then(response => {
                    if (response.ok) {
                        alert("Receita excluída com sucesso!");
                        window.location.href = "/ver-receitas";
                    } else {
                        throw new Error("Erro ao excluir a receita.");
                    }
                })
                .catch(error => {
                    console.error("Erro ao excluir receita:", error);
                    alert("Erro ao excluir receita. Tente novamente mais tarde.");
                });
        }
    }

    // Inicializar
    checkAuthentication().then(loadRecipeDetails);
});
