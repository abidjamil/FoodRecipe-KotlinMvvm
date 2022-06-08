package com.example.foodrecipe.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Results(
    val approved_at: Int,
    val aspect_ratio: String,
    val canonical_id: String,
    val cook_time_minutes: Int,
    val country: String,
    val created_at: Int,
    val description: String,
    val draft_status: String,
    val id: Int,
    val is_one_top: Boolean,
    val is_shoppable: Boolean,
    val keywords: String,
    val language: String,
    val name: String,
    val num_servings: Int,
    val nutrition_visibility: String,
    val original_video_url: String,
    val prep_time_minutes: Int,
    val promotion: String,
    val seo_title: String,
    val servings_noun_plural: String,
    val servings_noun_singular: String,
    val show_id: Int,
    val slug: String,
    val thumbnail_alt_text: String,
    val thumbnail_url: String,
    val tips_and_ratings_enabled: Boolean,
    val total_time_minutes: Int,
    val updated_at: Int,
    val video_url: String,
    val yields: String
) : Parcelable