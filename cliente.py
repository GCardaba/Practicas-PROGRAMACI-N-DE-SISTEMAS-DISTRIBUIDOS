import xmlrpc.client

def main():
    IP = "localhost"
    PUERTO = 1100
    servidor = xmlrpc.client.ServerProxy(f"http://{IP}:{PUERTO}/")

    menu = """
------------------

[-1] => Salir
[0] => Sumar
[1] => Restar
[2] => Multiplicar
[3] => Dividir
[4] => Raiz Cuadrada
[5] => Potencia

Elige: """

    while True:
        try:
            eleccion = input(menu)
            eleccion = int(eleccion)

            if eleccion == -1:
                break
            if eleccion == 5:
                numero1 = float(input("Ingresa el número de la base: "))
                numero2 = float(input("Ingresa el número exponente: "))

            else :
                numero1 = float(input("Ingresa el número 1: "))
                numero2 = 0.0

            if eleccion != 4 and eleccion !=5:
                numero2 = float(input("Ingresa el número 2: "))

            try:
                if eleccion == 0:
                    resultado = servidor.sumar(numero1, numero2)
                elif eleccion == 1:
                    resultado = servidor.restar(numero1, numero2)
                elif eleccion == 2:
                    resultado = servidor.multiplicar(numero1, numero2)
                elif eleccion == 3:
                    resultado = servidor.dividir(numero1, numero2)
                elif eleccion == 4:
                    resultado = servidor.sqrt(numero1)
                elif eleccion == 5: # Nueva funcionalidad añadida
                    resultado = servidor.elevar(numero1, numero2)
                else:
                    print("Opción no válida")
                    continue

                print(f"Resultado => {resultado}")
                input("Presiona ENTER para continuar")

            except Exception as e:
                print(f"Error: {e}")
                input("Presiona ENTER para continuar")

        except ValueError:
            print("Entrada no válida")
            continue

if __name__ == "__main__":
    main()