package urban.sandbox.threads.threads2.servidor;

import java.util.concurrent.ThreadFactory;

public class FabricaDeThreads implements ThreadFactory {

	private ThreadFactory defaultFactory;

    public FabricaDeThreads(ThreadFactory defaultFactory) {
        this.defaultFactory = defaultFactory;
    }
	
	@Override
	public Thread newThread(Runnable r) {
		//criando uma thread usando para fabrica padrão
        Thread thread = defaultFactory.newThread(r); 

        //personalizando a thread
        thread.setUncaughtExceptionHandler(new TratadorDeExcecao());
        return thread;
	}

}
