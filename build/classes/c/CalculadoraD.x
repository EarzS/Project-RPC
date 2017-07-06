/* Manejo de operaciones */

struct operadores {
	float a;
	float b;
};

struct operadores_promedio {
	float vector<>;
	int tamano;
};

struct operadores_raiz {
	float a;
};

struct operacion{
	operadores op;
	char simbolo[3];
	float resultado;
};

program CALCULADORAD{
	version CALCULADORAD_VERSION{
		float SUMA(operadores)=1;
		float RESTA(operadores)=2;
		float MULTIPLICACION(operadores)=3;
		float DIVISION(operadores)=4;
		float PROMEDIO(operadores_promedio)=5;
		float POTENCIA(operadores)=6;
		float RAIZ(operadores_raiz)=7;
	} = 1;
} = 0x31234567;
