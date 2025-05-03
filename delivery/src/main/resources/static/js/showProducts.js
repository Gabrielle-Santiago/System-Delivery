function setupModal() {
  const modal = document.getElementById("modal");
  const closeModalBtn = document.getElementById("closeModalBtn");

  closeModalBtn.addEventListener("click", () => {
    modal.style.display = "none";
  });

  window.addEventListener("click", (event) => {
    if (event.target === modal) {
      modal.style.display = "none";
    }
  });

  document.addEventListener("click", (event) => {
    if (event.target.classList.contains("open-modal-btn")) {
      const valor = parseFloat(event.target.dataset.valor).toFixed(2);

      document.querySelectorAll(".price, .details span:last-child").forEach(el => {
        el.textContent = `R$${valor}`;
      });

      modal.style.display = "flex";

      setTimeout(() => {
        setupCardValidation();
      }, 100);

    }
  });
}

document.addEventListener("DOMContentLoaded", async () => {
  setupModal();

  try {
    const response = await fetch("/admin/home");
    if (!response.ok) throw new Error("Erro ao buscar produtos");

    const produtos = await response.json();
    const container = document.getElementById("produtosContainer");

    produtos.forEach(produto => {
      const card = document.createElement("div");
      card.classList.add("card");

      card.innerHTML = `
        <div class="card-title">${produto.name}</div>
        <div class="card-content">
          <img src="/api/upload/view/${produto.imgName}" 
               alt="${produto.name}" 
               style="width: 300px; height: 300px;">
          <div>Descrição: ${produto.describe}</div> 
          <div>Valor do produto: R$ ${produto.valueProduct.toFixed(2)}</div> 
          <div class="card-button">
            <button class="open-modal-btn" data-valor="${produto.valueProduct}">Comprar</button>
          </div>
        </div>
      `;

      container.appendChild(card);
    });

  } catch (error) {
    console.error("Erro ao carregar produtos:", error);
  }
});

function setupCardValidation() {
  const numeroInput = document.getElementById("numero-cartao");
  const bandeiraInput = document.getElementById("bandeira");

  numeroInput.addEventListener("blur", async () => {
    const numero = numeroInput.value.trim();

    if (!numero) {
      alert("Preencha o número do cartão.");
      bandeiraInput.value = "";
      return;
    }

    try {
      const response = await fetch("/payment/number-card", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ numeroCartao: numero })
      });

      const result = await response.json();
      bandeiraInput.value = result.bandeira || "Desconhecida";
      alert(`Cartão reconhecido como: ${result.bandeira}`);

    } catch (error) {
      bandeiraInput.value = "";
      alert("Erro ao verificar o cartão. Verifique os dados ou tente novamente.");
      console.error(error);
    }
  });
}

document.getElementById("checkoutForm").addEventListener("submit", async (event) => {
  event.preventDefault(); 

  const numero = document.getElementById("numero-cartao").value.trim();
  const bandeira = document.getElementById("bandeira").value.trim();

  try {
    const response = await fetch("/payment/card-flag", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        numberCard: numero,
        type_card: bandeira,
      })
    });

    if (!response.ok) throw new Error("Erro ao processar o pagamento");

    const result = await response.json();
    alert("Pagamento realizado com sucesso!");
    document.getElementById("modal").style.display = "none";
    console.log("Resposta do pagamento:", result);

  } catch (error) {
    alert("Erro ao finalizar o pagamento.");
    console.error(error);
  }
});

