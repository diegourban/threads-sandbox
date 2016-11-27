package urban.sandbox.threads.threads2.servidor;

import java.lang.Thread.UncaughtExceptionHandler;

public class TratadorDeExcecao implements UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println("Deu exceção da Thread " + t.getName() + ", " + e.getMessage());
	}

}
