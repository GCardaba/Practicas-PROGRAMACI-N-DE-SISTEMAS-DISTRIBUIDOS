import unittest
from math import isclose
from servidor import Calculadora


class TestCalculadora(unittest.TestCase):

    def setUp(self):
        self.calc = Calculadora()

    def mostrar_resultado(self, operacion, esperado, obtenido):
        resultado = "✅ Correcto" if esperado == obtenido or isclose(
            esperado, obtenido, rel_tol=1e-4) else "❌ Incorrecto"
        print(
            f"{operacion} => Esperado: {esperado}, Obtenido: {obtenido} => {resultado}"
        )

    def test_sumar(self):
        resultado = self.calc.sumar(5, 3)
        self.mostrar_resultado("Sumar(5, 3)", 8, resultado)
        self.assertEqual(resultado, 8)

        resultado = self.calc.sumar(-5, -2)
        self.mostrar_resultado("Sumar(-5, -2)", -7, resultado)
        self.assertEqual(resultado, -7)

    def test_restar(self):
        resultado = self.calc.restar(10, 4)
        self.mostrar_resultado("Restar(10, 4)", 6, resultado)
        self.assertEqual(resultado, 6)

        resultado = self.calc.restar(4, 10)
        self.mostrar_resultado("Restar(4, 10)", -6, resultado)
        self.assertEqual(resultado, -6)

    def test_multiplicar(self):
        resultado = self.calc.multiplicar(3, 4)
        self.mostrar_resultado("Multiplicar(3, 4)", 12, resultado)
        self.assertEqual(resultado, 12)

        resultado = self.calc.multiplicar(-2, 5)
        self.mostrar_resultado("Multiplicar(-2, 5)", -10, resultado)
        self.assertEqual(resultado, -10)

    def test_dividir(self):
        resultado = self.calc.dividir(10, 2)
        self.mostrar_resultado("Dividir(10, 2)", 5, resultado)
        self.assertEqual(resultado, 5)

        resultado = self.calc.dividir(1, 3)
        self.mostrar_resultado("Dividir(1, 3)", 1 / 3, resultado)
        self.assertAlmostEqual(resultado, 1 / 3)

        print("Dividir(5, 0) => Esperado: ValueError")
        with self.assertRaises(ValueError):
            self.calc.dividir(5, 0)

    def test_sqrt(self):
        resultado = self.calc.sqrt(16)
        self.mostrar_resultado("Sqrt(16)", 4, resultado)
        self.assertEqual(resultado, 4)

        resultado = self.calc.sqrt(2)
        self.mostrar_resultado("Sqrt(2)", 1.41421, resultado)
        self.assertTrue(isclose(resultado, 1.41421, rel_tol=1e-4))

        print("Sqrt(-9) => Esperado: ValueError")
        with self.assertRaises(ValueError):
            self.calc.sqrt(-9)


if __name__ == '__main__':
    unittest.main()
