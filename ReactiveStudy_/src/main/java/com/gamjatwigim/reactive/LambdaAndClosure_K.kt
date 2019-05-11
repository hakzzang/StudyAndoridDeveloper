class LambdaAndClosure_K{
    fun main(){
        var outerVar = 3;
        {
            outerVar = 4;
        }()
        System.out.print(outerVar);//4
    }

    fun exam2(){
        val outerVar = 3;
        {
            outerVar = 4;
        }()
        System.out.println(outerVar);//compile error
    }
}