document.addEventListener("DOMContentLoaded", function () {
    // Obter o ID da receita a partir da URL
    const recipeId = window.location.pathname.split("/").pop();
    const recipeContainer = document.getElementById("recipe-container");

    // Função para carregar os detalhes da receita
    function loadRecipeDetails() {
        fetch(`/api/receitas/${recipeId}/detalhes`)
            .then(response => {
                if (!response.ok) {
                    throw new Error("Erro ao carregar detalhes da receita");
                }
                return response.json();
            })
            .then(recipe => {
                displayRecipeDetails(recipe);
            })
            .catch(error => {
                console.error("Erro ao carregar os detalhes da receita:", error);
                recipeContainer.innerHTML = "<p>Erro ao carregar os detalhes da receita. Tente novamente mais tarde.</p>";
            });
    }

    // Função para exibir os detalhes da receita
    function displayRecipeDetails(recipe) {
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
                            .sort((a, b) => a.ordem - b.ordem) // Ordenar os passos pela ordem
                            .map(passo => `<li>${passo.descricao}</li>`)
                            .join("")}
                    </ol>
                </div>
            </div>
        `;
    }

    // Carregar os detalhes da receita ao abrir a página
    loadRecipeDetails();
});
