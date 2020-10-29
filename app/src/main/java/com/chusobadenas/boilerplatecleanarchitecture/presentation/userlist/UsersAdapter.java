package com.chusobadenas.boilerplatecleanarchitecture.presentation.userlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.chusobadenas.boilerplatecleanarchitecture.common.util.UIUtils;
import com.chusobadenas.boilerplatecleanarchitecture.databinding.ItemUserBinding;
import com.chusobadenas.boilerplatecleanarchitecture.presentation.model.UserModel;
import dagger.hilt.android.qualifiers.ActivityContext;
import dagger.hilt.android.scopes.ActivityScoped;

import javax.inject.Inject;
import java.util.*;

/**
 * Adapter that manages a collection of {@link UserModel}.
 */
@ActivityScoped
public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {

  interface OnItemClickListener {

    void onUserItemClicked(UserModel userModel);
  }

  private List<UserModel> usersCollection;
  private final LayoutInflater layoutInflater;
  private OnItemClickListener onItemClickListener;

  @Inject
  public UsersAdapter(@ActivityContext Context context) {
    super();
    this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    this.usersCollection = Collections.emptyList();
  }

  @Override
  public int getItemCount() {
    return usersCollection == null ? 0 : usersCollection.size();
  }

  @NonNull
  @Override
  public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    ItemUserBinding binding = ItemUserBinding.inflate(layoutInflater, parent, false);
    return new UserViewHolder(binding);
  }

  @Override
  public void onBindViewHolder(@NonNull UserViewHolder holder, final int position) {
    Context context = holder.itemView.getContext();
    final UserModel userModel = usersCollection.get(position);
    UIUtils.loadImageUrl(context, holder.binding.imageUser, getImageUrl(userModel.getFullName()));
    holder.binding.textName.setText(userModel.getFullName());
    holder.itemView.setOnClickListener(view -> {
      if (onItemClickListener != null) {
        onItemClickListener.onUserItemClicked(userModel);
      }
    });
  }

  private String getImageUrl(String name) {
    String url = "https://c1.staticflickr.com/3/2673/4099652396_1b2387aa32.jpg";

    // Ugly, but the API doesn't provide images - so this is just for example image loading
    switch (name.toLowerCase()) {
      case "simon hill":
        url = "https://s3.amazonaws.com/rapgenius/Homer_Simpson_Vector_by_bark2008.png";
        break;
      case "peter graham":
        url = "https://s-media-cache-ak0.pinimg.com/736x/b9/fd/20/b9fd20744ad6f008787ffed46a0b7149.jpg";
        break;
      case "angelina johnston":
        url = "http://web.pdx.edu/~ngorman/nafiegorman/Draw%20Simpsons%20in%205%20steps1/img/lisa0.png";
        break;
      case "josh hunt":
        url = "http://vignette2.wikia.nocookie.net/simpsons/images/d/de/Barney_Gumble.png/revision/latest?cb=20120121184948&path-prefix=it";
        break;
      default:
        break;
    }

    return url;
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  void setUsersCollection(Collection<UserModel> usersCollection) {
    validateUsersCollection(usersCollection);
    this.usersCollection = (List<UserModel>) usersCollection;
    notifyDataSetChanged();
  }

  void setOnItemClickListener(OnItemClickListener onItemClickListener) {
    this.onItemClickListener = onItemClickListener;
  }

  private void validateUsersCollection(Collection<UserModel> usersCollection) {
    if (usersCollection == null) {
      throw new IllegalArgumentException("The list cannot be null");
    }
  }

  static class UserViewHolder extends RecyclerView.ViewHolder {

    private final ItemUserBinding binding;

    UserViewHolder(ItemUserBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }
  }
}
