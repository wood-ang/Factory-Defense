import com.wood.FactoryDefense.BlockTypes.BaseBlock

class Chunk<T : BaseBlock>(
    val width: Int,
    val height: Int,
    private val clazz: Class<T>
) {
    private val data = java.lang.reflect.Array
        .newInstance(clazz, width * height) as Array<T?>

    fun get(x: Int, y: Int): T? =
        data[y * width + x]

    fun set(x: Int, y: Int, value: T?) {
        data[y * width + x] = value
    }
}
