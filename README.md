🌌 Black Hole 3D Simulator - TSI IFMS
Este projeto consiste em um simulador tridimensional de um buraco negro, focado na visualização do disco de acreção e na distorção do tecido do espaço-tempo. Desenvolvido como projeto prático no curso de Tecnologia em Sistemas para Internet (TSI) do IFMS - Campus Campo Grande.

A aplicação utiliza o motor gráfico do JavaFX para processar milhares de partículas simultaneamente, simulando a física de um horizonte de eventos com alta performance.
.

🚀 Funcionalidades
Simulação de Gravidade em Tempo Real: Cálculo vetorial contínuo para gerenciar a órbita de mais de 4.000 partículas.

Disco de Acreção Caótico: Distribuição aleatória de matéria nos planos horizontal e vertical, criando um visual denso e turbulento.

Degradê de Temperatura Dinâmico: Partículas que variam do vermelho (baixa energia/distante) ao branco incandescente (alta energia/perto).

Manto do Espaço-Tempo Otimizado: Malha estrutural criada com TriangleMesh para representar a curvatura gravitacional com baixo consumo de memória.

Navegação 3D: Controle total de câmera (órbita, aproximação e rotação) em um ambiente de 360 graus.

🛠️ Tecnologias e Requisitos
Linguagem: Java 21+.

Engine: JavaFX 3D.

Build Tool: Maven.

Hardware: Recomenda-se o uso de aceleração por GPU (habilitada nativamente pelo JavaFX).


🧠 Lógica Física
O simulador utiliza uma versão adaptada da Lei da Gravitação Universal para calcular a aceleração das partículas a cada frame:
 



                                                $$F = G \frac{M}{r^2}$$


As partículas que ultrapassam o limite crítico do horizonte de eventos são removidas da renderização para otimizar o processamento e simular o consumo de matéria pelo buraco negro.

🎮 Controles

Tecla,Função
W / S,Zoom (Aproximar / Afastar)
A / D,Translação Lateral
Setas,Rotacionar câmera ao redor do centro
SPACE,Resetar câmera para a posição inicial



📦 Como Instalar e Rodar
Clone o repositório:

Bash
git clone https://github.com/seu-usuario/Black_hole3D.git
Certifique-se de configurar a memória RAM no seu ambiente (IDE ou Maven) para evitar erros de Heap Space:

VM Options: -Xms1024m -Xmx2048m.

Execute o projeto via Maven:

Bash
mvn javafx:run
Projeto desenvolvido por um aluno de TSI do IFMS.