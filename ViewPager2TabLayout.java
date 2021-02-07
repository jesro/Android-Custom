private class SwipeView extends RecyclerView.Adapter<SwipeView.ViewHolder> {

        private LayoutInflater layoutInflater;
        private Resources resources;

        public SwipeView() {
            layoutInflater = (LayoutInflater) MainActivity.this.getSystemService(LAYOUT_INFLATER_SERVICE);
            resources = getResources();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View inflate = layoutInflater.inflate(R.layout.pager, parent, false); // inflate(R.layout.pager, null);
            return new ViewHolder(inflate);
        }


        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.textView.setText("TEXT");
        }

        @Override
        public int getItemCount() {
            return MainActivity.this.arrayList.size();
        }

        private class ViewHolder extends RecyclerView.ViewHolder {
            private TextView textView;
            ViewHolder(View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.textView);
            }
        }
    }
    private class PageListener extends ViewPager2.OnPageChangeCallback {

        @Override
        public void onPageSelected(int position) {
            textView.setText(MainActivity.this.arrayList.get(position).getText());
            
        }
    }
