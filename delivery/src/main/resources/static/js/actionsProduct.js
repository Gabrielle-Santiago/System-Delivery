document.addEventListener("click", function (e) {
    if (e.target.closest(".rename")) {
        const item = e.target.closest(".rename");
        const id = item.getAttribute("data-id");

        const card = item.closest(".produto-item");
        const name = card.querySelector("h3").textContent;
        const value = card.querySelector("p:nth-of-type(1)").textContent.replace(/[^\d,.-]/g, "").replace(",", ".");
        const describe = card.querySelector("p:nth-of-type(2)").textContent;


        document.getElementById("edit-id").value = id;
        document.getElementById("edit-name").value = name;
        document.getElementById("edit-value").value = parseFloat(value);
        document.getElementById("edit-describe").value = describe;

        document.getElementById("editModal").style.display = "flex";
    }

    if (e.target.classList.contains("close-button")) {
        document.getElementById("editModal").style.display = "none";
    }
});

document.getElementById("editForm").addEventListener("submit", async function (e) {
  e.preventDefault();

  const id = document.getElementById("edit-id").value;
  const productData = {
    name: document.getElementById("edit-name").value,
    valueProduct: parseFloat(document.getElementById("edit-value").value),
    describe: document.getElementById("edit-describe").value
  };

  try {
    const response = await fetch(`/admin/${id}`, {
      method: "PATCH",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(productData)
    });

    if (response.ok) {
      alert("Produto atualizado com sucesso!");
      document.getElementById("editModal").style.display = "none";
      mostrarProduto();
    } else {
      alert("Erro ao atualizar o produto.");
    }
  } catch (error) {
    console.error(error);
    alert("Erro ao enviar requisição.");
  }
});

  

document.addEventListener("click", async function (event) {
    const deleteBtn = event.target.closest(".delete-btn");
  
    if (deleteBtn) {
      const parentElement = deleteBtn.closest(".delete"); 
      const id = parentElement.dataset.id;
  
      try {
        const response = await fetch(`/admin/${id}`, {
          method: "DELETE",
        });
  
        if (response.ok) {
          alert("Sucesso ao deletar o produto!!");
          parentElement.closest(".produto-item").remove();
        } else {
          alert("Erro ao deletar o produto");
        }
      } catch (error) {
        console.error("Erro ao deletar:", error);
      }
    }
  });
  