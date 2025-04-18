document.getElementById("formLogin").addEventListener("submit", async function (e) {
  e.preventDefault();

  const camposInput = document.getElementsByClassName("required");
  const spans = document.getElementsByClassName("span-required");

  const loginData = {
    login: camposInput[0].value,
    password: camposInput[1].value
  };

  loginValidate();
  passwordValidate();

  const hasError = Array.from(spans).some(span => span.style.display === "block");
  if (hasError) return;

  try {
    const response = await fetch("/auth/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(loginData),
      credentials: "include"
    });

    if (response.ok) {
      alert("Login realizado com sucesso!");
      checkUserRoleAndRedirect();
    } else {
      alert("Erro ao logar");
    }

  } catch (error) {
    console.error("Erro na requisição:", error);
  }

  function setError(index, message) {
    camposInput[index].style.border = "2px solid #e63636";
    spans[index].style.display = "block";
    spans[index].textContent = message;
  }

  function clearError(index) {
    camposInput[index].style.border = "";
    spans[index].style.display = "none";
    spans[index].textContent = "";
  }

  function loginValidate() {
    if (camposInput[0].value.trim().length < 5) {
      setError(0, "Por favor, escreva seu nome completo");
    } else {
      clearError(0);
    }
  }

  function passwordValidate() {
    if (camposInput[1].value.trim().length < 6) {
      setError(1, "A senha deve ter no mínimo 6 caracteres");
    } else {
      clearError(1);
    }
  }
});

function getCookie(name) {
  const matches = document.cookie.match(
    new RegExp(
      "(?:^|; )" +
      name.replace(/([$?*|{}\[\]\\\/+^])/g, "\\$1") +
      "=([^;]*)"
    )
  );
  return matches ? decodeURIComponent(matches[1]) : undefined;
}

function checkUserRoleAndRedirect() {
  const role = getCookie("role");

  if (!role) {
    alert("Não foi possível identificar o tipo de usuário.");
    return;
  }

  if (role === "ADMIN") {
    window.location.href = "/pag/homeAdmin";
  } else if (role === "USER") {
    window.location.href = "/pag/homeUser";
  } else {
    alert("Tipo de usuário desconhecido.");
  }
}
