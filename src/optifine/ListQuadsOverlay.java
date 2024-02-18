package optifine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.init.Blocks;

public class ListQuadsOverlay
{
    private final List<BakedQuad> listQuads = new ArrayList<BakedQuad>();
    private final List<IBlockState> listBlockStates = new ArrayList<IBlockState>();
    private final List<BakedQuad> listQuadsSingle = Collections.<BakedQuad>emptyList();

    public void addQuad(BakedQuad p_addQuad_1_, IBlockState p_addQuad_2_)
    {
        this.listQuads.add(p_addQuad_1_);
        this.listBlockStates.add(p_addQuad_2_);
    }

    public int size()
    {
        return this.listQuads.size();
    }

    public BakedQuad getQuad(int p_getQuad_1_)
    {
        return this.listQuads.get(p_getQuad_1_);
    }

    public IBlockState getBlockState(int p_getBlockState_1_)
    {
        return p_getBlockState_1_ >= 0 && p_getBlockState_1_ < this.listBlockStates.size() ? this.listBlockStates.get(p_getBlockState_1_) : Blocks.AIR.getDefaultState();
    }

    public List<BakedQuad> getListQuadsSingle(BakedQuad p_getListQuadsSingle_1_)
    {
        this.listQuadsSingle.set(0, p_getListQuadsSingle_1_);
        return this.listQuadsSingle;
    }

    public void clear()
    {
        this.listQuads.clear();
        this.listBlockStates.clear();
    }
}
