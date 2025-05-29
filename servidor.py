from xmlrpc.server import SimpleXMLRPCServer
from math import sqrt

class Calculadora:
    def sumar(self, numero1, numero2):
        return numero1 + numero2

    def restar(self, numero1, numero2):
        return numero1 - numero2

    def multiplicar(self, numero1, numero2):
        return numero1 * numero2

    def dividir(self, numero1, numero2):
        if numero2 == 0:
            raise ValueError("No se puede dividir entre 0")
        return numero1 / numero2

    def sqrt(self, numero1):
        if numero1 < 0:
            raise ValueError("No se puede calcular la raíz cuadrada de un número negativo")
        return sqrt(numero1)

    def elevar(self, numero1, numero2):
        return pow(numero1, numero2)

def main():
    PUERTO = 1100
    server = SimpleXMLRPCServer(("localhost", PUERTO))
    print(f"Servidor escuchando en el puerto {PUERTO}...")
    server.register_instance(Calculadora())
    server.register_function(lambda: "Calculadora", "get_name")
    server.serve_forever()

if __name__ == "__main__":
    main()