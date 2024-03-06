public class observer 
{
	private int score = 0;
 
    public void hitscore() 
    {
        score += 10; // 每次击中增加10分
        System.out.println("得分增加，当前得分：" + score);
    }
    

}

