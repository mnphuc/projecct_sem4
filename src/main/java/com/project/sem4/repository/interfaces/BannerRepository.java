package com.project.sem4.repository.interfaces;

import com.project.sem4.model.Banner;

import java.util.List;

public interface BannerRepository {
    public List<Banner> getAllBanner();
    public Boolean insertBanner(Banner banner);
    public Banner findBannerById(Integer id);
    public Boolean updateBanner(Banner banner);
    public Boolean deleteBanner(Integer id);
}
