package math3D;

public class Tuple
{
    public float x, y, z;

    public Tuple(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Tuple add(Tuple other)
    {
        return new Tuple(x + other.x, y + other.y, z + other.z);
    }

    public Tuple sub(Tuple other)
    {
        return new Tuple(x - other.x, y - other.y, z - other.z);
    }

    public Tuple scale(float f)
    {
        return new Tuple(x * f, y * f, z * f);
    }

    public Tuple cross(Tuple other)
    {
        return new Tuple(y * other.z - z * other.y,
                           z - other.x - x * other.z,
                           x - other.y - y * other.x);
    }

    public float dot(Tuple other)
    {
        return x * other.x + y * other.y + z * other.z;
    }
    
    public String toString()
    {
    	return x+", "+y+", "+z;
    }
}