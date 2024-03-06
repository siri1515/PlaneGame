

public class CollisionDetector implements Subject 
{
    private List<Observer> observers = new ArrayList<>();
    private Point bulletPosition; // 子弹位置
    private Point aircraftPosition; // 飞机位置
    
    //子弹位置
    public void setBulletPosition(Point bulletPosition) 
    {
        this.bulletPosition = bulletPosition;
    }
    //飞机位置
    public void setAircraftPosition(Point aircraftPosition) 
    {
        this.aircraftPosition = aircraftPosition;
    }

    @Override
    public void registerObserver(Observer observer) 
    {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) 
    {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() 
    {
        for (Observer observer : observers) 
        {
            observer.onHit();
        }
    }
    
    // 判断子弹是否击中飞机
    public void checkCollision() 
    {
        if (bulletPosition != null && aircraftPosition != null && 
        		bulletPosition.distance(aircraftPosition) < 5) 
        { 
            notifyObservers();
        }
    }
}