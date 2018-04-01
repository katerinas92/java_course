package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

// проверяемя число на простоту
public class PrimeTests {

  @Test
  // тест простого числа (цикл for)
  public void testPrimeFor() {
    Assert.assertTrue(Primes.isPrimeFor(Integer.MAX_VALUE));
  }

  @Test
  // тест не простого числа (цикл for)
  public void testNonPrimeFor() {
    Assert.assertFalse(Primes.isPrimeFor(Integer.MAX_VALUE - 2));
  }

  @Test
  // тест простого числа (цикл while)
  public void testPrimeWhile() {
    Assert.assertTrue(Primes.isPrimeWhile(Integer.MAX_VALUE));
  }

  @Test
  // тест не простого числа (цикл while)
  public void testNonPrimeWhile() {
    Assert.assertFalse(Primes.isPrimeWhile(Integer.MAX_VALUE - 2));
  }

  @Test
  // тест простого числа (цикл for с изменениями)
  public void testPrimeFast() {
    Assert.assertTrue(Primes.isPrimeFast(Integer.MAX_VALUE));
  }

  @Test (enabled = false)
  public void testPrimeLong() {
    long n = Integer.MAX_VALUE;
    Assert.assertTrue(Primes.isPrime(n));
  }

}
