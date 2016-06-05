package com.jadencode.main.util;

import com.jadencode.main.nbt.NBTTagCompound;

public class TaggingUtility<T>
{
    private NBTAdapter<T> adapter;
    public TaggingUtility(NBTAdapter<T> adapter)
    {
        this.adapter = adapter;
    }
    public boolean hasTag(String tagName)
    {
        return this.adapter.getTagCompound() != null ? this.adapter.getTagCompound().hasKey(tagName) : false;
    }
    public void removeTag(String tagName)
    {
        NBTTagCompound nbt = this.adapter.getTagCompound();
        if(nbt != null)
        {
            if(nbt.hasKey(tagName))
            {
                nbt.removeTag(tagName);
            }
        }
    }
    public boolean getBoolean(String tagName)
    {
        if(this.hasTag(tagName))
        {
            return this.adapter.getTagCompound().getBoolean(tagName);
        }
        return false;
    }
    public void setBoolean(String tagName, boolean val)
    {
        if(this.adapter.getTagCompound() != null)
        {
            this.adapter.getTagCompound().setBoolean(tagName, val);
        }
    }
    public void copyBoolean(TaggingUtility util, String tagName)
    {
        util.setBoolean(tagName, this.getBoolean(tagName));
    }
    public int getInt(String tagName)
    {
        if(this.hasTag(tagName))
        {
            return this.adapter.getTagCompound().getInteger(tagName);
        }
        return 0;
    }
    public void setInt(String tagName, int val)
    {
        if(this.adapter.getTagCompound() != null)
        {
            this.adapter.getTagCompound().setInteger(tagName, val);
        }
    }
    public void copyInt(TaggingUtility util, String tagName)
    {
        util.setInt(tagName, this.getInt(tagName));
    }
    public double getDouble(String tagName)
    {
        if(this.hasTag(tagName))
        {
            return this.adapter.getTagCompound().getDouble(tagName);
        }
        return 0D;
    }
    public void setDouble(String tagName, double val)
    {
        if(this.adapter.getTagCompound() != null)
        {
            this.adapter.getTagCompound().setDouble(tagName, val);
        }
    }
    public void copyDouble(TaggingUtility util, String tagName)
    {
        util.setDouble(tagName, this.getDouble(tagName));
    }
    public String getString(String tagName)
    {
        if(this.hasTag(tagName))
        {
            return this.adapter.getTagCompound().getString(tagName);
        }
        return "";
    }
    public void setString(String tagName, String val)
    {
        if(this.adapter.getTagCompound() != null)
        {
            this.adapter.getTagCompound().setString(tagName, val);
        }
    }
    public void copyString(TaggingUtility util, String tagName)
    {
        util.setString(tagName, this.getString(tagName));
    }
    public int[] getIntArray(String tagName)
    {
        if(this.hasTag(tagName))
        {
            return this.adapter.getTagCompound().getIntArray(tagName);
        }
        return new int[]{};
    }
    public void setIntArray(String tagName, int[] val)
    {
        if(this.adapter.getTagCompound() != null)
        {
            this.adapter.getTagCompound().setIntArray(tagName, val);
        }
    }
//	public static boolean hasTag(ItemStack stack, String compoundName, String tagName)
//	{
//		NBTTagCompound stackTagCompound = stack.getTagCompound();
//		if(stackTagCompound == null)
//		{
//			return false;
//		}
//		NBTTagCompound innerCompound = stackTagCompound.getCompoundTag(compoundName);
//
//		return innerCompound.hasKey(tagName);
//	}
//	public static void removeTag(ItemStack stack, String compoundName, String tagName)
//	{
//		NBTTagCompound stackTagCompound = stack.getTagCompound();
//		if(stackTagCompound == null)
//		{
//			return;
//		}
//		NBTTagCompound innerCompound = stackTagCompound.getCompoundTag(compoundName);
//
//		if(innerCompound.hasKey(tagName))
//		{
//			innerCompound.removeTag(tagName);
//		}
//	}
//	public static void copyBoolean(ItemStack toCopy, ItemStack copyTo, String compoundName, String tagName)
//	{
//		setBoolean(copyTo, compoundName, tagName, getBoolean(toCopy, compoundName, tagName));
//	}
//	public static void setBoolean(ItemStack stack, String compoundName, String tagName, boolean val)
//	{
//		NBTTagCompound stackTagCompound = stack.getTagCompound();
//		if(stackTagCompound == null)
//		{
//			stackTagCompound = new NBTTagCompound();
//			stack.setTagCompound(stackTagCompound);
//		}
//		NBTTagCompound stackTagCompoundCV = stackTagCompound.getCompoundTag(compoundName);
//
//		if(!stackTagCompound.hasKey(compoundName))
//		{
//			stackTagCompound.setTag(compoundName, stackTagCompoundCV);
//		}
//		stackTagCompoundCV.setBoolean(tagName, val);
//	}
//	public static boolean getBoolean(ItemStack stack, String compoundName, String tagName)
//	{
//        if(!hasTag(stack, compoundName, tagName))
//        {
//            return false;
//        }
//		else
//		{
//            NBTTagCompound stackTagCompound = stack.getTagCompound();
//
//            NBTTagCompound stackTagCompoundCV = stackTagCompound.getCompoundTag(compoundName);
//			if(stackTagCompoundCV == null)
//			{
//				return false;
//			}
//			else if(stackTagCompoundCV.hasKey(tagName))
//			{
//				return stackTagCompoundCV.getBoolean(tagName);
//			}
//			else
//			{
//				return false;
//			}
//		}
//	}
//	public static void copyInt(ItemStack toCopy, ItemStack copyTo, String compoundName, String tagName)
//	{
//		setInt(copyTo, compoundName, tagName, getInt(toCopy, compoundName, tagName));
//	}
//	public static void setInt(ItemStack stack, String compoundName, String tagName, int val)
//	{
//		NBTTagCompound stackTagCompound = stack.getTagCompound();
//		if(stackTagCompound == null)
//		{
//			stackTagCompound = new NBTTagCompound();
//			stack.setTagCompound(stackTagCompound);
//		}
//		NBTTagCompound stackTagCompoundCV = stackTagCompound.getCompoundTag(compoundName);
//
//		if(!stackTagCompound.hasKey(compoundName))
//		{
//			stackTagCompound.setTag(compoundName, stackTagCompoundCV);
//		}
//		stackTagCompoundCV.setInteger(tagName, val);
//	}
//	public static int getInt(ItemStack stack, String compoundName, String tagName)
//	{
//        if(!hasTag(stack, compoundName, tagName))
//        {
//            return 0;
//        }
//		else
//		{
//            NBTTagCompound stackTagCompound = stack.getTagCompound();
//
//            NBTTagCompound stackTagCompoundCV = stackTagCompound.getCompoundTag(compoundName);
//			if(stackTagCompoundCV == null)
//			{
//				return 0;
//			}
//			else if(stackTagCompoundCV.hasKey(tagName))
//			{
//				return stackTagCompoundCV.getInteger(tagName);
//			}
//			else
//			{
//				return 0;
//			}
//		}
//	}
//	public static void copyLong(ItemStack toCopy, ItemStack copyTo, String compoundName, String tagName)
//	{
//		setLong(copyTo, compoundName, tagName, getLong(toCopy, compoundName, tagName));
//	}
//	public static void setLong(ItemStack stack, String compoundName, String tagName, long val)
//	{
//		NBTTagCompound stackTagCompound = stack.getTagCompound();
//		if(stackTagCompound == null)
//		{
//			stackTagCompound = new NBTTagCompound();
//			stack.setTagCompound(stackTagCompound);
//		}
//		NBTTagCompound stackTagCompoundCV = stackTagCompound.getCompoundTag(compoundName);
//
//		if(!stackTagCompound.hasKey(compoundName))
//		{
//			stackTagCompound.setTag(compoundName, stackTagCompoundCV);
//		}
//		stackTagCompoundCV.setLong(tagName, val);
//	}
//	public static long getLong(ItemStack stack, String compoundName, String tagName)
//	{
//        if(!hasTag(stack, compoundName, tagName))
//        {
//            return 0L;
//        }
//		else
//		{
//            NBTTagCompound stackTagCompound = stack.getTagCompound();
//
//            NBTTagCompound stackTagCompoundCV = stackTagCompound.getCompoundTag(compoundName);
//			if(stackTagCompoundCV == null)
//			{
//				return 0L;
//			}
//			else if(stackTagCompoundCV.hasKey(tagName))
//			{
//				return stackTagCompoundCV.getLong(tagName);
//			}
//			else
//			{
//				return 0L;
//			}
//		}
//	}
//	public static void copyFloat(ItemStack toCopy, ItemStack copyTo, String compoundName, String tagName)
//	{
//		setFloat(copyTo, compoundName, tagName, getFloat(toCopy, compoundName, tagName));
//	}
//	public static void setFloat(ItemStack stack, String compoundName, String tagName, float val)
//	{
//		NBTTagCompound stackTagCompound = stack.getTagCompound();
//		if(stackTagCompound == null)
//		{
//			stackTagCompound = new NBTTagCompound();
//			stack.setTagCompound(stackTagCompound);
//		}
//		NBTTagCompound stackTagCompoundCV = stackTagCompound.getCompoundTag(compoundName);
//
//		if(!stackTagCompound.hasKey(compoundName))
//		{
//			stackTagCompound.setTag(compoundName, stackTagCompoundCV);
//		}
//		stackTagCompoundCV.setFloat(tagName, val);
//	}
//	public static float getFloat(ItemStack stack, String compoundName, String tagName)
//	{
//        if(!hasTag(stack, compoundName, tagName))
//        {
//            return 0F;
//        }
//		else
//		{
//            NBTTagCompound stackTagCompound = stack.getTagCompound();
//
//            NBTTagCompound stackTagCompoundCV = stackTagCompound.getCompoundTag(compoundName);
//			if(stackTagCompoundCV == null)
//			{
//				return 0;
//			}
//			else if(stackTagCompoundCV.hasKey(tagName))
//			{
//				return stackTagCompoundCV.getFloat(tagName);
//			}
//			else
//			{
//				return 0;
//			}
//		}
//	}
//	public static void copyDouble(ItemStack toCopy, ItemStack copyTo, String compoundName, String tagName)
//	{
//		setDouble(copyTo, compoundName, tagName, getDouble(toCopy, compoundName, tagName));
//	}
//	public static void setDouble(ItemStack stack, String compoundName, String tagName, double val)
//	{
//		NBTTagCompound stackTagCompound = stack.getTagCompound();
//		if(stackTagCompound == null)
//		{
//			stackTagCompound = new NBTTagCompound();
//			stack.setTagCompound(stackTagCompound);
//		}
//		NBTTagCompound stackTagCompoundCV = stackTagCompound.getCompoundTag(compoundName);
//
//		if(!stackTagCompound.hasKey(compoundName))
//		{
//			stackTagCompound.setTag(compoundName, stackTagCompoundCV);
//		}
//		stackTagCompoundCV.setDouble(tagName, val);
//	}
//	public static double getDouble(ItemStack stack, String compoundName, String tagName)
//	{
//		if(!hasTag(stack, compoundName, tagName))
//        {
//            return 0D;
//        }
//		else
//		{
//            NBTTagCompound stackTagCompound = stack.getTagCompound();
//
//            NBTTagCompound stackTagCompoundCV = stackTagCompound.getCompoundTag(compoundName);
//			if(stackTagCompoundCV == null)
//			{
//				return 0;
//			}
//			else if(stackTagCompoundCV.hasKey(tagName))
//			{
//				return stackTagCompoundCV.getDouble(tagName);
//			}
//			else
//			{
//				return 0;
//			}
//		}
//	}
//	public static void copyString(ItemStack toCopy, ItemStack copyTo, String compoundName, String tagName)
//	{
//		setString(copyTo, compoundName, tagName, getString(toCopy, compoundName, tagName));
//	}
//	public static void setString(ItemStack stack, String compoundName, String tagName, String val)
//	{
//		NBTTagCompound stackTagCompound = stack.getTagCompound();
//		if(stackTagCompound == null)
//		{
//			stackTagCompound = new NBTTagCompound();
//			stack.setTagCompound(stackTagCompound);
//		}
//		NBTTagCompound stackTagCompoundCV = stackTagCompound.getCompoundTag(compoundName);
//
//		if(!stackTagCompound.hasKey(compoundName))
//		{
//			stackTagCompound.setTag(compoundName, stackTagCompoundCV);
//		}
//		stackTagCompoundCV.setString(tagName, val);
//	}
//	public static String getString(ItemStack stack, String compoundName, String tagName)
//	{
//        if(!hasTag(stack, compoundName, tagName))
//        {
//            return "";
//        }
//		else
//		{
//            NBTTagCompound stackTagCompound = stack.getTagCompound();
//
//            NBTTagCompound stackTagCompoundCV = stackTagCompound.getCompoundTag(compoundName);
//			if(stackTagCompoundCV == null)
//			{
//				return "";
//			}
//			else if(stackTagCompoundCV.hasKey(tagName))
//			{
//				return stackTagCompoundCV.getString(tagName);
//			}
//			else
//			{
//				return "";
//			}
//		}
//	}
//	public static void copyIntArray(ItemStack toCopy, ItemStack copyTo, String compoundName, String tagName)
//	{
//		setIntArray(copyTo, compoundName, tagName, getIntArray(toCopy, compoundName, tagName));
//	}
//	public static void setIntArray(ItemStack stack, String compoundName, String tagName, int[] val)
//	{
//		NBTTagCompound stackTagCompound = stack.getTagCompound();
//		if(stackTagCompound == null)
//		{
//			stackTagCompound = new NBTTagCompound();
//			stack.setTagCompound(stackTagCompound);
//		}
//		NBTTagCompound stackTagCompoundCV = stackTagCompound.getCompoundTag(compoundName);
//
//		if(!stackTagCompound.hasKey(compoundName))
//		{
//			stackTagCompound.setTag(compoundName, stackTagCompoundCV);
//		}
//		stackTagCompoundCV.setIntArray(tagName, val);
//	}
//	public static int[] getIntArray(ItemStack stack, String compoundName, String tagName)
//	{
//        if(!hasTag(stack, compoundName, tagName))
//        {
//            return new int[]{};
//        }
//		else
//		{
//            NBTTagCompound stackTagCompound = stack.getTagCompound();
//
//            NBTTagCompound stackTagCompoundCV = stackTagCompound.getCompoundTag(compoundName);
//			if(stackTagCompoundCV == null)
//			{
//				return new int[]{};
//			}
//			else if(stackTagCompoundCV.hasKey(tagName))
//			{
//				return stackTagCompoundCV.getIntArray(tagName);
//			}
//			else
//			{
//				return new int[]{};
//			}
//		}
//	}
    public double[] getDoubleArray(String tagName)
    {
        if(this.hasTag(tagName))
        {
            NBTTagCompound nbt = this.adapter.getTagCompound().getCompoundTag(tagName);
            int size = nbt.getInteger("size");
            double[] ret = new double[size];
            for(int i = 0; i < size; i++)
            {
                ret[i] = nbt.getDouble(i + "");
            }
        }
        return new double[]{};
    }
    public void setDoubleArray(String tagName, double[] val)
    {
        if(this.adapter.getTagCompound() != null)
        {
            int size = val.length;
            NBTTagCompound nbt = new NBTTagCompound();
            for(int i = 0; i < size; i++)
            {
                nbt.setDouble(i + "", val[i]);
            }
            this.adapter.getTagCompound().setTag(tagName, nbt);
        }
    }
    public void copyDoubleArray(TaggingUtility util, String tagName)
    {
        util.setDoubleArray(tagName, this.getDoubleArray(tagName));
    }
}