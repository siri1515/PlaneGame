public class Observationport 
{	
	public interface Observer {
	    void hitscore();
	}

	// 主题接口
	public interface Subject {
	    void registerObserver(Observer observer);
	    void removeObserver(Observer observer);
	    void notifyObservers();
	}

}
